import React, { useEffect, useState } from "react";
import http from "../api/axios"; // Putanja do http klijenta
import "../styles/SvePorudzbine.css";

export default function SvePorudzbine() {
  const [orders, setOrders] = useState([]);
  const [kolaciById, setKolaciById] = useState({}); // Čuva kolače mapirane po ID-ju radi ispisa naziva
  const [loading, setLoading] = useState(true);
  const [err, setErr] = useState("");

  // Pomoćna funkcija za čitljiviji prikaz datuma
  const fmtDate = (s) => (s ? s.replace("T", " ").slice(0, 10) : "N/A");

  // Dohvatanje naziva kolača preko njegovog ID-ja
  const uzmiNazivKolaca = (id) => kolaciById[id]?.naziv || `Kolač #${id}`;

  // Učitavanje porudžbina i kolača sa backenda
  const load = async () => {
    try {
      setLoading(true);
      const [oRes, kRes] = await Promise.all([
        http.get("/porudzbina"), // Povlači sve porudžbine iz baze
        http.get("/kolac"), // Povlači sve kolače da bismo znali njihove nazive
      ]);

      setOrders(oRes.data || []);

      // Pretvaramo niz kolača u objekat ključ-vrednost { id: kolac } radi brže pretrage
      const map = {};
      (kRes.data || []).forEach((k) => (map[k.id] = k));
      setKolaciById(map);

      setErr("");
    } catch (e) {
      console.error("Greška pri učitavanju:", e);
      setErr("Nije moguće učitati podatke sa servera.");
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    load();
  }, []);

  // Funkcija za brisanje porudžbine iz baze (Traženo!)
  const deleteOrder = async (id) => {
    if (
      !window.confirm(
        `Da li ste sigurni da želite da obrišete porudžbinu #${id}?`,
      )
    )
      return;
    try {
      await http.delete(`/porudzbina/${id}`); // Šalje DELETE zahtev na backend
      // Filtriramo stanje tako da porudžbina odmah nestane sa ekrana bez osvežavanja stranice
      setOrders(orders.filter((o) => o.id !== id));
      alert("Porudžbina je uspešno obrisana.");
    } catch (e) {
      console.error(e);
      alert("Brisanje porudžbine nije uspelo.");
    }
  };

  // Otvaranje/zatvaranje detalja (stavki) ispod reda porudžbine
  const toggleItems = (id) => {
    setOrders(orders.map((o) => (o.id === id ? { ...o, _open: !o._open } : o)));
  };

  if (loading)
    return (
      <div className="text-center py-5">
        <h3>Učitavanje podataka...</h3>
      </div>
    );
  if (err)
    return (
      <div className="text-center py-5 text-danger">
        <h3>{err}</h3>
      </div>
    );

  return (
    <div className="sve-porudzbine-page">
      <div className="container">
        <div className="d-flex justify-content-between align-items-center mb-4 px-3">
          <div>
            <h1>Sve Porudžbine</h1>
            <p className="text-muted">Interfejs za radnike poslastičarnice</p>
          </div>
          <button className="btn-osvezi" onClick={load}>
            Osveži
          </button>
        </div>

        <div className="table-wrap">
          <table className="table">
            <thead>
              <tr>
                <th>ID</th>
                <th>Korisnik</th>
                <th>Datum</th>
                <th className="right">Broj stavki</th>
                <th className="right">Ukupan iznos</th>
                <th className="text-center">Akcije</th>
              </tr>
            </thead>
            <tbody>
              {orders.map((o) => (
                <React.Fragment key={o.id}>
                  <tr>
                    <td>
                      <strong>#{o.id}</strong>
                    </td>
                    <td>{o.korisnikId || o.userId || "Gost"}</td>
                    <td>{fmtDate(o.datumKreiranja || o.createdAt)}</td>
                    <td className="right">{o.stavkePorudzbine?.length ?? 0}</td>
                    <td className="right fw-bold text-success">
                      {o.ukupanIznos}.00 RSD
                    </td>
                    <td className="text-center">
                      <button
                        className="btn-pogledaj"
                        onClick={() => toggleItems(o.id)}
                      >
                        {o._open ? "Sakrij" : "Stavke"}
                      </button>
                      <button
                        className="btn-obrisi"
                        onClick={() => deleteOrder(o.id)}
                      >
                        Obriši
                      </button>
                    </td>
                  </tr>

                  {/* Unutrašnji panel za kolače */}
                  {o._open && (
                    <tr>
                      <td colSpan="6" className="p-3">
                        <div className="items-row-container">
                          <div className="items-title">Naručeni kolači:</div>
                          {o.stavkePorudzbine &&
                          o.stavkePorudzbine.length > 0 ? (
                            <ul className="items-list">
                              {o.stavkePorudzbine.map((it, index) => (
                                <li key={it.id || index}>
                                  <span className="kolac-naziv-tabela">
                                    {uzmiNazivKolaca(it.kolacId)}
                                  </span>
                                  <span className="kolac-detalji-tabela">
                                    {it.kolicina} kom × {it.cena} RSD |{" "}
                                    {it.iznos} RSD
                                  </span>
                                </li>
                              ))}
                            </ul>
                          ) : (
                            <span className="items-prazno">
                              Nema zabeleženih stavki.
                            </span>
                          )}
                        </div>
                      </td>
                    </tr>
                  )}
                </React.Fragment>
              ))}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
}
