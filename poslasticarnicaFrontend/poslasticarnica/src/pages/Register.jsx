import React, { useState, useEffect } from "react";
import api from "../api/axios";
import { useNavigate, Link } from "react-router-dom";
import "../styles/Register.css";

function Register() {
  const navigate = useNavigate();
  const [mesta, setMesta] = useState([]);
  const [formData, setFormData] = useState({
    ime: "",
    prezime: "",
    username: "",
    password: "",
    email: "",
    brojTelefona: "",
    mestoId: "",
  });

  // 1. Učitavamo mesta čim se otvori stranica
  useEffect(() => {
    api
      .get("/mesto")
      .then((res) => setMesta(res.data))
      .catch((err) => console.error("Greška pri učitavanju mesta", err));
  }, []);

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleRegister = async (e) => {
    e.preventDefault();
    try {
      // Šaljemo RegisterRequest backendu
      await api.post("/auth/register", formData);
      alert("Uspešna registracija! Sada se možete prijaviti.");
      navigate("/login");
    } catch (err) {
      const errorMsg = err.response?.data || "Nepoznata greška";
      alert("Greška sa bekenda: " + JSON.stringify(errorMsg));
    }
  };

  return (
    <div className="register-page">
      <div className="card register-card p-4">
        <div className="card-body">
          <h3 className="text-center mb-4">Postani naš član</h3>
          <p className="text-center text-muted mb-5">
            Pridružite se ljubiteljima zdravih poslastica
          </p>

          <form onSubmit={handleRegister}>
            <div className="row">
              {/* Leva kolona */}
              <div className="col-md-6">
                <div className="mb-3">
                  <label>Ime</label>
                  <input
                    name="ime"
                    className="form-control"
                    onChange={handleChange}
                    required
                  />
                </div>
                <div className="mb-3">
                  <label>Prezime</label>
                  <input
                    name="prezime"
                    className="form-control"
                    onChange={handleChange}
                    required
                  />
                </div>
                <div className="mb-3">
                  <label>Email</label>
                  <input
                    type="email"
                    name="email"
                    className="form-control"
                    onChange={handleChange}
                    required
                  />
                </div>
              </div>

              {/* Desna kolona */}
              <div className="col-md-6">
                <div className="mb-3">
                  <label>Korisničko ime</label>
                  <input
                    name="username"
                    className="form-control"
                    onChange={handleChange}
                    required
                  />
                </div>
                <div className="mb-3">
                  <label>Lozinka</label>
                  <input
                    type="password"
                    name="password"
                    className="form-control"
                    onChange={handleChange}
                    required
                  />
                </div>
                <div className="mb-3">
                  <label>Broj telefona</label>
                  <input
                    name="brojTelefona"
                    className="form-control"
                    onChange={handleChange}
                    required
                  />
                </div>
              </div>
            </div>

            {/* Izbor mesta - donji deo */}
            <div className="mb-4">
              <label>Vaše mesto</label>
              <select
                name="mestoId"
                className="form-select"
                onChange={handleChange}
                required
              >
                <option value="">Izaberite grad...</option>
                {mesta.map((m) => (
                  <option key={m.idMesto} value={m.idMesto}>
                    {m.naziv}
                  </option>
                ))}
              </select>
            </div>

            <button type="submit" className="btn btn-register w-100 mt-2">
              KREIRAJ NALOG
            </button>
          </form>

          <p className="text-center mt-4" style={{ fontSize: "14px" }}>
            Već imaš nalog?{" "}
            <Link
              to="/login"
              style={{
                color: "#7d9475",
                fontWeight: "bold",
                textDecoration: "none",
              }}
            >
              Prijavi se
            </Link>
          </p>
        </div>
      </div>
    </div>
  );
}

export default Register;
