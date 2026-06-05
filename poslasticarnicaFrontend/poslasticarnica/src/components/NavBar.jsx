import React from "react";
import { Link, NavLink, useNavigate } from "react-router-dom";
import "../styles/NavBar.css";

function NavBar() {
  const navigate = useNavigate();

  // Naša logika za proveru ulogovanosti (čita iz localStorage)
  const token = localStorage.getItem("token");
  const role = localStorage.getItem("role");
  const username = localStorage.getItem("username");

  const handleLogout = () => {
    localStorage.clear();
    navigate("/login");
    window.location.reload(); // Osvežavamo da bi Navbar resetovao stanje
  };

  // Stil za aktivni link (da pobeli ili dobije liniju kad smo na toj stranici)
  const activeStyle = ({ isActive }) => ({
    color: isActive ? "#ffffff" : "rgba(255,255,255,0.8)",
    fontWeight: isActive ? "700" : "500",
    borderBottom: isActive ? "2px solid white" : "none",
  });

  return (
    <nav className="custom-navbar">
      <div className="container d-flex justify-content-between align-items-center">
        <Link to="/" className="navbar-brand-custom d-flex align-items-center">
          <img src="/logo.png" alt="Logo" className="navbar-logo-img me-2" />
          Zdravi<span>Zalogaji</span>
        </Link>

        {/* GLAVNI LINKOVI */}
        <div className="nav-links">
          <NavLink to="/" style={activeStyle}>
            POČETNA
          </NavLink>

          {/* Ako je ulogovan KUPAC, vidi svoje porudžbine */}
          {role === "KUPAC" && (
            <>
              <NavLink to="/kolac" style={activeStyle}>
                PONUDA KOLAČA
              </NavLink>
              <NavLink to="/porudzbina" style={activeStyle}>
                KORPA
              </NavLink>
            </>
          )}

          {/* Ako je ulogovan RADNIK, vidi panel za upravljanje */}
          {role === "RADNIK" && (
            <>
              <NavLink to="/kolac" style={activeStyle}>
                UPRAVLJANJE KOLAČIMA
              </NavLink>
              <NavLink to="/korisnik" style={activeStyle}>
                KORISNICI
              </NavLink>
              <NavLink to="/sve_porudzbine" style={activeStyle}>
                SVE PORUDŽBINE
              </NavLink>
            </>
          )}
        </div>

        {/* DESNA STRANA: LOGIN/LOGOUT */}
        <div className="nav-actions">
          {!token ? (
            <>
              <Link to="/login" className="btn-nav-outline">
                PRIJAVA
              </Link>
              <Link to="/register" className="btn-nav-fill">
                REGISTRACIJA
              </Link>
            </>
          ) : (
            <div className="user-section">
              <span className="user-name">👤 {username}</span>
              <button onClick={handleLogout} className="btn-logout">
                ODJAVA
              </button>
            </div>
          )}
        </div>
      </div>
    </nav>
  );
}

export default NavBar;
