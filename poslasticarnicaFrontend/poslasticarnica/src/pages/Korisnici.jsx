import React, { useState, useEffect } from "react";
import axios from "axios";
import "../styles/Korisnici.css";

const Korisnici = () => {
  const [korisnici, setKorisnici] = useState([]);
  const [mesta, setMesta] = useState([]);
  const [searchTerm, setSearchTerm] = useState("");
  const [isEditing, setIsEditing] = useState(false);
  const [showForm, setShowForm] = useState(false);

  const [formData, setFormData] = useState({
    idKorisnik: "",
    ime: "",
    prezime: "",
    brojTelefona: "",
    email: "",
    username: "",
    password: "",
    mestoId: "",
  });

  const API_BASE = "http://localhost:8080/api/korisnik";
  const API_MESTA = "http://localhost:8080/api/mesto";

  useEffect(() => {
    const inicijalizuj = async () => {
      await ucitajMesta();
      await ucitajSve();
    };
    inicijalizuj();
  }, []);

  const ucitajSve = async () => {
    try {
      // Uzimatoken iz localStorage
      const token = localStorage.getItem("token");

      const res = await axios.get(API_BASE, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      setKorisnici(res.data);
    } catch (err) {
      console.error("Greška pri učitavanju korisnika:", err);
    }
  };

  const ucitajMesta = async () => {
    try {
      const res = await axios.get(API_MESTA);
      setMesta(res.data);
    } catch (err) {
      console.error("Greška pri učitavanju mesta:", err);
    }
  };

  // FUNKCIJA ZA PRIKAZ NAZIVA MESTA
  const dajNazivMesta = (korisnik) => {
    if (!mesta || mesta.length === 0) return "Učitavanje...";

    // Koristimo mestoId jer se tako zove u tvom DTO-u
    const idIzBaze = korisnik.mestoId;

    if (!idIzBaze) return "Nije uneto";

    // Tražimo u listi mesta ono čiji je idMesto jednak korisnikovom mestoId
    const pronadjeno = mesta.find(
      (m) => String(m.idMesto) === String(idIzBaze),
    );

    return pronadjeno ? pronadjeno.naziv : `Nepoznato (${idIzBaze})`;
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (formData.password && formData.password.length < 8) {
      alert("Greška pri kreiranju korisnika.");
      return; // Prekida izvršavanje funkcije ako lozinka nije dobra
    }
    const token = localStorage.getItem("token");
    try {
      const config = {
        headers: { Authorization: `Bearer ${token}` },
      };

      if (isEditing) {
        // SK11: Izmena (Drugi parametar su podaci, treći je config sa tokenom)
        await axios.put(`${API_BASE}/${formData.idKorisnik}`, formData, config);
        alert("Korisnik je uspešno ažuriran!");
      } else {
        // SK10: Dodavanje (Drugi parametar su podaci, treći je config)
        await axios.post(API_BASE, formData, config);
        alert("Novi korisnik je uspešno dodat!");
      }
      setShowForm(false);
      resetForm();
      ucitajSve();
    } catch (err) {
      console.error(err);
      alert("Greška pri čuvanju podataka! Proverite da li imate dozvolu.");
    }
  };

  const handleObrisi = async (id) => {
    if (window.confirm("Da li ste sigurni?")) {
      const token = localStorage.getItem("token");
      try {
        await axios.delete(`${API_BASE}/${id}`, {
          headers: { Authorization: `Bearer ${token}` },
        });
        alert("Korisnik je obrisan.");
        ucitajSve();
      } catch (err) {
        alert("Brisanje korisnika nije uspelo!");
      }
    }
  };

  const pripremiIzmenu = (k) => {
    // Ako backend šalje ravan ID, moramo ga spakovati u objekat za formu
    const formatiranoMesto =
      typeof k.mesto === "object" ? k.mesto : { idMesto: k.idMesto || k.mesto };

    setFormData({
      ...k,
      mesto: formatiranoMesto,
    });
    setIsEditing(true);
    setShowForm(true);
  };

  const resetForm = () => {
    setFormData({
      idKorisnik: "",
      ime: "",
      prezime: "",
      brojTelefona: "",
      email: "",
      username: "",
      password: "",
      mestoId: "",
    });
    setIsEditing(false);
  };

  const filtrirani = korisnici.filter(
    (k) =>
      k.ime.toLowerCase().includes(searchTerm.toLowerCase()) ||
      k.prezime.toLowerCase().includes(searchTerm.toLowerCase()) ||
      k.username.toLowerCase().includes(searchTerm.toLowerCase()),
  );

  return (
    <div className="zz-page-wrapper">
      <header className="zz-header-section">
        <h1 className="zz-main-title">Korisnici</h1>
        <button
          className="zz-btn-add"
          onClick={() => {
            resetForm();
            setShowForm(!showForm);
          }}
        >
          {showForm ? "ZATVORI FORMU" : "DODAJ NOVOG KORISNIKA"}
        </button>
      </header>

      {showForm && (
        <div className="zz-form-overlay">
          <form onSubmit={handleSubmit} className="zz-card-form">
            <h3>{isEditing ? "Izmena Korisnika" : "Novi Korisnik"}</h3>
            <div className="zz-input-grid">
              <input
                type="text"
                placeholder="Ime"
                value={formData.ime}
                onChange={(e) =>
                  setFormData({ ...formData, ime: e.target.value })
                }
                required
              />
              <input
                type="text"
                placeholder="Prezime"
                value={formData.prezime}
                onChange={(e) =>
                  setFormData({ ...formData, prezime: e.target.value })
                }
                required
              />
              <input
                type="text"
                placeholder="Telefon"
                value={formData.brojTelefona}
                onChange={(e) =>
                  setFormData({ ...formData, brojTelefona: e.target.value })
                }
                required
              />
              <input
                type="email"
                placeholder="Email"
                value={formData.email}
                onChange={(e) =>
                  setFormData({ ...formData, email: e.target.value })
                }
                required
              />
              <input
                type="text"
                placeholder="Username"
                value={formData.username}
                onChange={(e) =>
                  setFormData({ ...formData, username: e.target.value })
                }
                required
              />
              {!isEditing && (
                <input
                  type="password"
                  placeholder="Lozinka"
                  value={formData.password}
                  onChange={(e) =>
                    setFormData({ ...formData, password: e.target.value })
                  }
                  required
                />
              )}
              <select
                value={formData.mestoId || ""}
                onChange={(e) =>
                  setFormData({ ...formData, mestoId: e.target.value })
                }
                required
              >
                <option value="">Izaberite mesto...</option>
                {mesta.map((m) => (
                  <option key={m.idMesto} value={m.idMesto}>
                    {m.naziv}
                  </option>
                ))}
              </select>
            </div>
            <div className="zz-form-actions">
              <button type="submit" className="zz-btn-submit">
                SAČUVAJ
              </button>
              <button
                type="button"
                onClick={() => setShowForm(false)}
                className="zz-btn-cancel"
              >
                ODUSTANI
              </button>
            </div>
          </form>
        </div>
      )}

      <div className="zz-search-container">
        <input
          type="text"
          placeholder="Pretraži po imenu, prezimenu ili username-u..."
          className="zz-search-input"
          value={searchTerm}
          onChange={(e) => setSearchTerm(e.target.value)}
        />
      </div>

      <div className="zz-table-container">
        <table className="zz-table">
          <thead>
            <tr>
              <th>Ime i Prezime</th>
              <th>Kontakt</th>
              <th>Mesto</th>
              <th>Korisničko ime</th>
              <th>Akcije</th>
            </tr>
          </thead>
          <tbody>
            {filtrirani.map((k) => (
              <tr key={k.idKorisnik}>
                <td data-label="Ime i Prezime">
                  <strong>
                    {k.ime} {k.prezime}
                  </strong>
                </td>
                <td data-label="Kontakt">
                  {k.email}
                  <br />
                  <small>{k.brojTelefona}</small>
                </td>
                <td data-label="Mesto">{dajNazivMesta(k)}</td>
                <td data-label="Username">@{k.username}</td>
                <td data-label="Akcije" className="zz-actions">
                  <span
                    className="zz-link-edit"
                    onClick={() => pripremiIzmenu(k)}
                  >
                    Izmeni
                  </span>
                  <span
                    className="zz-link-delete"
                    onClick={() => handleObrisi(k.idKorisnik)}
                  >
                    Obriši
                  </span>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default Korisnici;
