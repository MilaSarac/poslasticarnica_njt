import "./App.css";
import Home from "./pages/Home";
import Login from "./pages/Login";
import Register from "./pages/Register";
import Kolaci from "./pages/Kolaci";
import NavBar from "./components/NavBar";
import Footer from "./components/Footer";
import DodajKolac from "./pages/DodajKolac";
import IzmeniKolac from "./pages/IzmeniKolac";
import Porudzbina from "./pages/Porudzbina";
import SvePorudzbine from "./pages/SvePorudzbine";
import Korisnici from "./pages/Korisnici";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";

function App() {
  return (
    <div className="App">
      <Router>
        <NavBar />
        <Routes>
          <Route path="/login" element={<Login />} />
          <Route path="/register" element={<Register />} />
          <Route path="/" exact element={<Home />} />
          <Route path="/kolac" exact element={<Kolaci />} />
          <Route path="/dodaj_kolac" element={<DodajKolac />} />
          <Route path="/izmeni_kolac/:id" element={<IzmeniKolac />} />
          <Route path="/korisnik" element={<Korisnici />} />
          <Route path="/porudzbina" element={<Porudzbina />} />
          <Route path="/sve_porudzbine" element={<SvePorudzbine />} />
        </Routes>
        <Footer />
      </Router>
    </div>
  );
}

export default App;
