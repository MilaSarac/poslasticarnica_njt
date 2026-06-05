import React from "react";
import "../styles/Home.css";
import { Link } from "react-router-dom";

function Home() {
  const username = localStorage.getItem("username");
  const role = localStorage.getItem("role");

  const handleLogout = () => {
    localStorage.clear(); // Brišemo sve podatke
    window.location.reload(); // Osvežavamo stranicu
  };

  // Link ka profesionalnoj slici koja podseća na Divi Bakery temu
  const imageUrl =
    "https://i.pinimg.com/1200x/15/37/29/1537293aae48a6cbb373a701ae6fc7e3.jpg";

  return (
    <div className="home" style={{ backgroundImage: `url(${imageUrl})` }}>
      <div className="headerContainer">
        <h6 className="subtitle">RUČNO PRAVLJENI ZDRAVI KOLAČI</h6>
        <h1>Zdravi Zalogaji</h1>
        <p>Uživanje u hrani sa brigom o vašem telu</p>

        {/*{username ? (
          // Ako je ulogovan
          <div className="mt-4">
            <h3>Dobrodošla nazad, {username}!</h3>
            <p>
              Tvoja uloga je: <strong>{role}</strong>
            </p>
            <button onClick={handleLogout} className="btn btn-outline-danger">
              Odjavi se
            </button>
          </div>
        ) : (
          // Ako NIKO nije ulogovan
          <div className="mt-4">
            <p>Prijavite se da biste naručili kolače.</p>
            <Link to="/login" className="btn btn-primary m-2">
              Prijavi se
            </Link>
            <Link to="/register" className="btn btn-secondary m-2">
              Registruj se
            </Link>
          </div>
        )}*/}

        <div className="buttonGroup">
          <Link to="/kolac">
            <button className="main-btn"> Pogledaj ponudu kolača </button>
          </Link>
          {/*<div className="auth-links">
            <Link to="/login">Prijava Kupca</Link>
            <span className="divider">
              {" "}
              <strong>|</strong>
            </span>
            <Link to="/login">Prijava Radnika</Link>
            </div>*/}
        </div>
      </div>
    </div>
  );
}

export default Home;
