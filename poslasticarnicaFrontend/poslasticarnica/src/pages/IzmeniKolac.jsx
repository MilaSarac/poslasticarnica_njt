import React, { useState, useEffect } from "react";
import api from "../api/axios";
import { useNavigate, useParams } from "react-router-dom";
import "../styles/DodajKolac.css"; // Koristi isti stil kao za dodavanje

function IzmeniKolac() {
  const navigate = useNavigate();
  const { id } = useParams(); // Dobijamo ID kolača iz URL-a (npr. /izmeni-kolac/123)
  const [formData, setFormData] = useState({
    id: id,
    naziv: "",
    opis: "",
    cenaPoKomadu: "",
    imageUrl: "",
    vrsta: "",
  });

  const kategorije = ["TORTE", "BEZ_GLUTENA", "BEZ_SECERA", "PROTEINSKI"];

  // 1. Učitavamo podatke kolača kad se stranica otvori
  useEffect(() => {
    api
      .get(`/kolac/${id}`)
      .then((res) => setFormData(res.data))
      .catch((err) => {
        alert("Greška pri učitavanju kolača!");
        navigate("/kolac");
      });
  }, [id, navigate]); // Dodajemo id i navigate u zavisnosti

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      // Šaljemo PUT zahtev na http://localhost:8080/api/kolac/{id}
      await api.put(`/kolac/${id}`, formData);
      alert("Kolač je uspešno ažuriran!");
      navigate("/kolac");
    } catch (err) {
      console.error(err);
      alert("Greška pri ažuriranju kolača.");
    }
  };

  return (
    <div className="add-kolac-page">
      {" "}
      {/* Koristi isti stil kao za dodavanje */}
      <div className="container">
        <div className="card add-kolac-card shadow">
          <div className="card-body p-5">
            <h2 className="form-title text-center mb-4">Izmeni Kolač</h2>
            <form onSubmit={handleSubmit}>
              <div className="row">
                <div className="col-md-6 mb-3">
                  <label className="form-label-custom">NAZIV KOLAČA</label>
                  <input
                    name="naziv"
                    className="form-control-custom"
                    value={formData.naziv} // Vrednost iz state-a
                    onChange={handleChange}
                    required
                  />
                </div>

                <div className="col-md-6 mb-3">
                  <label className="form-label-custom">
                    VRSTA (KATEGORIJA)
                  </label>
                  <select
                    name="vrsta"
                    className="form-select-custom"
                    value={formData.vrsta} // Vrednost iz state-a
                    onChange={handleChange}
                    required
                  >
                    <option value="">Izaberite kategoriju...</option>
                    {kategorije.map((kat) => (
                      <option key={kat} value={kat}>
                        {kat.replace("_", " ")}
                      </option>
                    ))}
                  </select>
                </div>
              </div>

              {/* Ostala polja (opis, cena, imageUrl) */}
              {/* ... */}

              <div className="mb-4">
                <label className="form-label-custom">
                  CENA PO KOMADU (RSD)
                </label>
                <input
                  type="number"
                  name="cenaPoKomadu"
                  className="form-control-custom"
                  value={formData.cenaPoKomadu} // Vrednost iz state-a
                  onChange={handleChange}
                  required
                />
              </div>
              <div className="mb-3">
                <label className="form-label-custom">LINK DO SLIKE (URL)</label>
                <input
                  name="imageUrl"
                  className="form-control-custom"
                  value={formData.imageUrl} // Vrednost iz state-a
                  onChange={handleChange}
                  required
                />
              </div>
              <div className="mb-4">
                <label className="form-label-custom">OPIS KOLAČA</label>
                <textarea
                  name="opis"
                  className="form-control-custom"
                  rows="3"
                  value={formData.opis} // Vrednost iz state-a
                  onChange={handleChange}
                  required
                ></textarea>
              </div>

              <div className="d-flex gap-3">
                <button
                  type="submit"
                  className="btn-save flex-grow-1"
                  onClick={() => navigate("/kolac")}
                >
                  SAČUVAJ IZMENE
                </button>
                <button
                  type="button"
                  className="btn-cancel"
                  onClick={() => navigate("/kolac")}
                >
                  ODUSTANI
                </button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  );
}

export default IzmeniKolac;
