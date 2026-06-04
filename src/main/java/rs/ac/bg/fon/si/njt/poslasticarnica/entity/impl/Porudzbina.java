/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.si.njt.poslasticarnica.entity.impl;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;
import java.util.List;
import rs.ac.bg.fon.si.njt.poslasticarnica.entity.DomainEntity;

/**
 *
 * @author Mila
 */

@Entity
@Table(name = "porudzbina")
public class Porudzbina implements DomainEntity{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date datumKreiranja;
    private Double ukupanIznos;

    @ManyToOne()
    @JoinColumn(name = "korisnik_id")
    private Korisnik korisnik;

    @ManyToOne
    @JoinColumn(name = "radnik_id")
    private Radnik radnik;
    
    //ovo mappedBy govori da se inofrmacije o tome kojoj porudzbini pripada koja stavka 
    //nalaze u klasi StavkaPorudzbine u polju porudzbina
    //orphanPremoval kada se obrise porudzbina brise se i stavka jer ne pripada vise porudzbini
    @OneToMany(mappedBy = "porudzbina", cascade = CascadeType.ALL, fetch =FetchType.EAGER, orphanRemoval = true)
    private List<StavkaPorudzbine> stavkePorudzbine;

    public Porudzbina() {
    }
    
    public Porudzbina(Long id) {
        this.id = id;
    }

    public Porudzbina(Long id, Date datumKreiranja, Double ukupanIznos, Korisnik korisnik, Radnik radnik, List<StavkaPorudzbine> stavkePorudzbine) {
        this.id = id;
        this.datumKreiranja = datumKreiranja;
        this.ukupanIznos = ukupanIznos;
        this.korisnik = korisnik;
        this.radnik = radnik;
        this.stavkePorudzbine = stavkePorudzbine;
    }
    
    //Zbog polja Porudzbina u StavkaPorudzbine
    public void addStavka (StavkaPorudzbine sp){
        sp.setPorudzbina(this);
        this.stavkePorudzbine.add(sp);
    }
    
    public void removeStavka(StavkaPorudzbine sp){
        sp.setPorudzbina(null);
        this.stavkePorudzbine.remove(sp);
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDatumKreiranja() {
        return datumKreiranja;
    }

    public void setDatumKreiranja(Date datumKreiranja) {
        this.datumKreiranja = datumKreiranja;
    }

    public Double getUkupanIznos() {
        return ukupanIznos;
    }

    public void setUkupanIznos(Double ukupanIznos) {
        this.ukupanIznos = ukupanIznos;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    public Radnik getRadnik() {
        return radnik;
    }

    public void setRadnik(Radnik radnik) {
        this.radnik = radnik;
    }

    public List<StavkaPorudzbine> getStavkePorudzbine() {
        return stavkePorudzbine;
    }

    public void setStavkePorudzbine(List<StavkaPorudzbine> stavkePorudzbine) {
        this.stavkePorudzbine = stavkePorudzbine;
    }

    
}
