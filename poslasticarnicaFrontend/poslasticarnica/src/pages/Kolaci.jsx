import React, { useState, useEffect } from "react";
import api from "../api/axios";
import "../styles/Kolaci.css";
import { useNavigate } from "react-router-dom";

function Kolaci() {
  const [kolaci, setKolaci] = useState([]);
  const [filter, setFilter] = useState("SVE");
  const [loading, setLoading] = useState(true);
  const [searchTerm, setSearchTerm] = useState("");

  // Objekat koji čuva količinu za svaki id kolača posebno
  // Format će biti: { [kolacId]: kolicina }
  const [kolicine, setKolicine] = useState({});

  const navigate = useNavigate();
  const role = localStorage.getItem("role");

  const kategorije = [
    "SVE",
    "TORTE",
    "BEZ_GLUTENA",
    "BEZ_SECERA",
    "PROTEINSKI",
  ];

  useEffect(() => {
    fetchKolaci();
  }, []);

  const fetchKolaci = () => {
    api
      .get("/kolac")
      .then((res) => {
        setKolaci(res.data);
        setLoading(false);
      })
      .catch((err) => console.error(err));
  };

  // Funkcija koja menja količinu samo za određeni kolač
  const handleInputChange = (id, value) => {
    if (value === "") {
      setKolicine({ ...kolicine, [id]: "" });
    } else {
      const num = parseInt(value);
      if (num >= 1) {
        setKolicine({ ...kolicine, [id]: num });
      }
    }
  };

  // funkcija za dodavanje koja prima id i kolač
  const dodajUKorpu = (kolac) => {
    // Ako za ovaj id ne postoji upisana količina, podrazumeva se 1
    const odabranaKolicina =
      kolicine[kolac.id] === "" || !kolicine[kolac.id]
        ? 1
        : Number(kolicine[kolac.id]);

    const trenutnaKorpa = JSON.parse(localStorage.getItem("korpa")) || [];
    const postojecaStavka = trenutnaKorpa.find((item) => item.id === kolac.id);

    let izmenjenaKorpa;
    if (postojecaStavka) {
      izmenjenaKorpa = trenutnaKorpa.map((item) =>
        item.id === kolac.id
          ? { ...item, kolicina: item.kolicina + odabranaKolicina }
          : item,
      );
    } else {
      izmenjenaKorpa = [
        ...trenutnaKorpa,
        { ...kolac, kolicina: odabranaKolicina },
      ];
    }

    localStorage.setItem("korpa", JSON.stringify(izmenjenaKorpa));
    alert(`${kolac.naziv} (${odabranaKolicina}x) dodat u korpu!`);

    // Resetujemo količinu za taj konkretan kolač nazad na 1
    setKolicine({ ...kolicine, [kolac.id]: 1 });
  };

  const handleDelete = async (id) => {
    if (window.confirm("Obrisati ovaj kolač?")) {
      try {
        await api.delete(`/kolac/${id}`);
        fetchKolaci();
        alert("Uspešno obrisan kolač!");
      } catch (err) {
        alert("Greška prilikom brisanja kolača!");
      }
    }
  };

  const prikazaniKolaci = kolaci.filter((k) => {
    const matchesCategory = filter === "SVE" || k.vrsta === filter;
    const matchesSearch = k.naziv
      .toLowerCase()
      .includes(searchTerm.toLowerCase());
    return matchesCategory && matchesSearch;
  });

  if (loading)
    return <div className="text-center mt-5">Učitavanje kolača...</div>;

  return (
    <div className="kolaci-page">
      <div className="container">
        <div className="d-flex justify-content-between align-items-center mb-5">
          <h2 className="section-title">Naši Zdravi Zalogaji</h2>
          {role === "RADNIK" && (
            <button
              className="btn-add-new"
              onClick={() => navigate("/dodaj_kolac")}
            >
              DODAJ NOVI KOLAČ
            </button>
          )}
        </div>

        <div className="search-container mb-4">
          <input
            type="text"
            className="form-control search-input"
            placeholder="Pretraži po nazivu (npr. Čokolada, Voćni...)"
            value={searchTerm}
            onChange={(e) => setSearchTerm(e.target.value)}
          />
        </div>

        <div className="filter-bar mb-5">
          {kategorije.map((kat) => (
            <button
              key={kat}
              className={`filter-btn ${filter === kat ? "active" : ""}`}
              onClick={() => setFilter(kat)}
            >
              {kat.replace("_", " ")}
            </button>
          ))}
        </div>

        <div className="row g-4">
          {prikazaniKolaci.map((k) => {
            // Izvlačimo trenutnu količinu za ovaj kolač iz objekta (ako je nema, default je 1)
            const trenutnaKolicina =
              kolicine[k.id] !== undefined ? kolicine[k.id] : 1;
            const jedinica = k.vrsta === "TORTE" ? "kg" : "kom";

            return (
              <div className="col-xl-3 col-lg-4 col-md-6 col-sm-12" key={k.id}>
                <div className="card h-100 kolac-card shadow-sm">
                  <div className="kolac-img-wrapper">
                    <img src={k.imageUrl} className="kolac-img" alt={k.naziv} />
                  </div>

                  <div className="card-body text-center p-4">
                    <span className="kolac-vrsta">{k.vrsta}</span>
                    <h4 className="kolac-naziv">{k.naziv}</h4>
                    <p
                      className="text-muted small mb-3"
                      style={{ minHeight: "40px" }}
                    >
                      {k.opis}
                    </p>
                    <h5 className="kolac-price mb-4">{k.cenaPoKomadu} RSD</h5>

                    {/* TVOJ KOD ZA INPUT I KOLIČINU DIREKTNO U MAP-U */}
                    {role === "KUPAC" && (
                      <div
                        className="quantity-control"
                        style={{
                          marginBottom: "15px",
                          display: "flex",
                          alignItems: "center",
                          justifyContent: "center",
                          gap: "8px",
                        }}
                      >
                        <label className="small fw-semibold">Količina:</label>
                        <input
                          type="number"
                          min="1"
                          value={trenutnaKolicina}
                          onChange={(e) =>
                            handleInputChange(k.id, e.target.value)
                          }
                          style={{
                            width: "70px",
                            padding: "5px",
                            borderRadius: "5px",
                            border: "1px solid #ccc",
                            textAlign: "center",
                          }}
                        />
                        <span style={{ color: "#666", fontWeight: "500" }}>
                          {jedinica}
                        </span>
                      </div>
                    )}

                    <div className="d-flex gap-2 justify-content-center">
                      {role === "KUPAC" && (
                        <button
                          className="btn-action btn-buy w-100"
                          onClick={() => dodajUKorpu(k)}
                        >
                          DODAJ U KORPU
                        </button>
                      )}

                      {role === "RADNIK" && (
                        <>
                          <button
                            className="btn-action btn-edit flex-grow-1"
                            onClick={() => navigate(`/izmeni_kolac/${k.id}`)}
                          >
                            IZMENI
                          </button>
                          <button
                            className="btn-action btn-delete flex-grow-1"
                            onClick={() => handleDelete(k.id)}
                          >
                            OBRIŠI
                          </button>
                        </>
                      )}
                    </div>
                  </div>
                </div>
              </div>
            );
          })}
        </div>
      </div>
    </div>
  );
}

export default Kolaci;
