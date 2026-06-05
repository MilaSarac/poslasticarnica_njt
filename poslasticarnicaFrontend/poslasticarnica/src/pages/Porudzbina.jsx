import React, { useState, useEffect } from "react";
import api from "../api/axios";
import { useNavigate } from "react-router-dom";
import "../styles/Porudzbina.css";

function Porudzbina() {
  const [korpa, setKorpa] = useState([]);
  const navigate = useNavigate();

  // Pri učitavanju stranice povlačimo sve što je ubačeno u korpu
  useEffect(() => {
    const sacuvanaKorpa = JSON.parse(localStorage.getItem("korpa")) || [];
    setKorpa(sacuvanaKorpa);
  }, []);

  // Funkcija koja kompletno uklanja kolač iz korpe i ažurira localStorage
  const ukloniIzKorpe = (id) => {
    const novaKorpa = korpa.filter((item) => item.id !== id);
    setKorpa(novaKorpa);
    localStorage.setItem("korpa", JSON.stringify(novaKorpa));
  };

  // Računanje ukupnog iznosa za celu porudžbinu
  const ukupno = korpa.reduce(
    (sum, item) => sum + item.cenaPoKomadu * item.kolicina,
    0,
  );

  // Slanje porudžbine na backend API
  const potvrdiPorudzbinu = async () => {
    if (korpa.length === 0) return alert("Korpa je prazna!");

    const korisnikId = localStorage.getItem("userId");
    const radnikId = localStorage.getItem("radnikId") || 1;

    const stavkeKonvertovano = korpa.map((item) => ({
      kolicina: Number(item.kolicina),
      kolacId: item.id,
      cena: item.cenaPoKomadu,
      iznos: item.cenaPoKomadu * item.kolicina,
    }));

    const novaPorudzbinaDto = {
      korisnikId: korisnikId ? Number(korisnikId) : null,
      radnikId: Number(radnikId),
      datumKreiranja: new Date().toISOString(),
      ukupanIznos: ukupno,
      stavkePorudzbine: stavkeKonvertovano,
    };

    try {
      await api.post("/porudzbina", novaPorudzbinaDto);
      alert("Porudžbina uspešno kreirana!");

      setKorpa([]);
      localStorage.removeItem("korpa");
      navigate("/kolac");
    } catch (err) {
      console.error("Greška pri kreiranju porudžbine:", err);
      alert(
        err.response?.data?.message ||
          "Greška pri kreiranju porudžbine na serveru.",
      );
    }
  };

  return (
    <div className="naruci-page">
      <div className="container">
        <div className="text-center">
          <h1 className="section-title">Vaša Korpa</h1>
        </div>

        <div className="row justify-content-center">
          <div className="col-lg-8">
            {korpa.length === 0 ? (
              <div className="cart-card p-5 text-center rounded-4">
                <h4 className="text-muted mb-4">
                  Vaša korpa je trenutno prazna.
                </h4>
                <button
                  className="btn btn-success"
                  onClick={() => navigate("/kolac")}
                >
                  Nazad na poslastice
                </button>
              </div>
            ) : (
              <>
                {/* LISTA STAVKI IZ KORPE */}
                {korpa.map((item) => {
                  const jedinica = item.vrsta === "TORTE" ? "kg" : "kom";

                  return (
                    <div key={item.id} className="item-card mb-3 p-4">
                      <div className="row align-items-center text-center text-md-start">
                        <div className="col-md-8">
                          <h4 className="kolac-naziv">{item.naziv}</h4>
                          <div className="kolac-detalji">
                            Jedinična cena:{" "}
                            <span>{item.cenaPoKomadu}.00 RSD</span>
                          </div>
                          <div className="kolac-detalji">
                            Količina:{" "}
                            <span>
                              {item.kolicina} {jedinica}
                            </span>
                          </div>
                          <div className="kolac-detalji mt-2">
                            Ukupno:{" "}
                            <span className="kolac-cena-ukupno">
                              {item.cenaPoKomadu * item.kolicina} RSD
                            </span>
                          </div>
                        </div>
                        <div className="col-md-4 text-md-end mt-3 mt-md-0">
                          <button
                            className="btn-delete-item"
                            onClick={() => ukloniIzKorpe(item.id)}
                          >
                            Ukloni
                          </button>
                        </div>
                      </div>
                    </div>
                  );
                })}

                {/* PANEL ZA FINALE PORUDŽBINE */}
                <div className="cart-card p-4 text-center mt-4">
                  <h3 className="mb-4">
                    Ukupno za uplatu: <span>{ukupno}.00 RSD</span>
                  </h3>
                  <div className="d-flex justify-content-center">
                    <button
                      className="btn btn-success text-uppercase"
                      onClick={potvrdiPorudzbinu}
                    >
                      Nastavi na plaćanje
                    </button>
                  </div>
                </div>
              </>
            )}
          </div>
        </div>
      </div>
    </div>
  );
}

export default Porudzbina;
