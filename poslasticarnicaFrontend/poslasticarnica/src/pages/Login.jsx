import React, { useState } from "react";
import api from "../api/axios";
import { useNavigate, Link } from "react-router-dom";
import "../styles/Login.css"; // Uvozimo novi CSS

function Login() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();

  const handleLogin = async (e) => {
    e.preventDefault();
    try {
      const res = await api.post("/auth/login", { username, password });

      localStorage.setItem("token", res.data.token);
      localStorage.setItem("role", res.data.role);
      localStorage.setItem("username", res.data.username);
      localStorage.setItem("userId", res.data.id);
      alert("Dobrodošli u Zdravi Zalogaji aplikaciju!");
      navigate("/");
      window.location.reload(); // Osvežavamo da Navbar vidi promene
    } catch (err) {
      alert("Pogrešno korisničko ime ili lozinka.");
    }
  };

  return (
    <div className="login-wrapper">
      <div className="login-card text-center">
        <h3>Dobrodošli nazad</h3>
        <form onSubmit={handleLogin}>
          <div className="mb-4 text-start">
            <label className="form-label">Korisničko ime</label>
            <input
              type="text"
              className="form-control custom-input"
              placeholder="Unesite username"
              onChange={(e) => setUsername(e.target.value)}
              required
            />
          </div>
          <div className="mb-4 text-start">
            <label className="form-label">Lozinka</label>
            <input
              type="password"
              className="form-control custom-input"
              placeholder="••••••••"
              onChange={(e) => setPassword(e.target.value)}
              required
            />
          </div>
          <button type="submit" className="login-btn w-100">
            PRIJAVI SE
          </button>
        </form>

        <p className="register-link">
          Nemaš nalog? <Link to="/register">Registruj se ovde</Link>
        </p>
      </div>
    </div>
  );
}

export default Login;
