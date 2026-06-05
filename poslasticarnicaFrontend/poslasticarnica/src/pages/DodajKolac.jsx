import React, { useState } from "react";
import api from "../api/axios";
import { useNavigate } from "react-router-dom";
import "../styles/DodajKolac.css";

function DodajKolac() {
  const navigate = useNavigate();

  // Stanje forme - mora se poklapati sa tvojim KolacDto na backendu
  const [formData, setFormData] = useState({
    naziv: "",
    opis: "",
    cenaPoKomadu: "",
    imageUrl: "",
    vrsta: "", // Enum: TORTE, BEZ_GLUTENA, BEZ_SECERA, PROTEINSKI
  });

  const kategorije = ["TORTE", "BEZ_GLUTENA", "BEZ_SECERA", "PROTEINSKI"];

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      // Šaljemo POST zahtev na http://localhost:8080/api/kolac
      await api.post("/kolac", formData);
      alert("Kolač je uspešno dodat u ponudu!");
      navigate("/kolac"); // Vraćamo se na listu svih kolača
    } catch (err) {
      console.error(err);
      alert("Greška pri dodavanju kolača. Proverite podatke.");
    }
  };

  return (
    <div className="add-kolac-page">
      <div className="container">
        <div className="card add-kolac-card shadow">
          <div className="card-body p-5">
            <h2 className="form-title text-center mb-4">Novi Zdravi Zalogaj</h2>
            <p className="text-center text-muted mb-5">
              Unesite detalje o novom kolaču koji želite da dodate u ponudu.
            </p>

            <form onSubmit={handleSubmit}>
              <div className="row">
                <div className="col-md-6 mb-3">
                  <label className="form-label-custom">NAZIV KOLAČA</label>
                  <input
                    name="naziv"
                    className="form-control-custom"
                    placeholder="npr. Čoko proteinska bomba"
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

              <div className="mb-3">
                <label className="form-label-custom">LINK DO SLIKE (URL)</label>
                <input
                  name="imageUrl"
                  className="form-control-custom"
                  placeholder="https://images.unsplash.com/..."
                  onChange={handleChange}
                  required
                />
              </div>

              <div className="mb-3">
                <label className="form-label-custom">
                  CENA PO KOMADU (RSD)
                </label>
                <input
                  type="number"
                  name="cenaPoKomadu"
                  className="form-control-custom"
                  placeholder="450"
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
                  placeholder="Kratak opis sastojaka..."
                  onChange={handleChange}
                  required
                ></textarea>
              </div>

              <div className="d-flex gap-3">
                <button type="submit" className="btn-save flex-grow-1">
                  SAČUVAJ KOLAČ
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

export default DodajKolac;
