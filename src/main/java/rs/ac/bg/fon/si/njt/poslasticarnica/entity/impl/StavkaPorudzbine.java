/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.si.njt.poslasticarnica.entity.impl;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import rs.ac.bg.fon.si.njt.poslasticarnica.entity.DomainEntity;

/**
 *
 * @author Mila
 */

@Entity
@Table(name = "stavka_porudzbine")
public class StavkaPorudzbine implements DomainEntity{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rb;
    private Double iznos;
    private Double cena;
    private Long kolicina;

    @ManyToOne
    @JoinColumn(name = "porudzbina_id")
    private Porudzbina porudzbina;
    
    @ManyToOne
    @JoinColumn(name = "kolac_id")
    private Kolac kolac;

    public StavkaPorudzbine() {
    }

    public StavkaPorudzbine(Long rb, Double iznos, Double cena, Long kolicina, Porudzbina porudzbina, Kolac kolac) {
        this.rb = rb;
        this.iznos = iznos;
        this.cena = cena;
        this.kolicina = kolicina;
        this.porudzbina = porudzbina;
        this.kolac = kolac;
    }

    public Porudzbina getPorudzbina() {
        return porudzbina;
    }

    public void setPorudzbina(Porudzbina porudzbina) {
        this.porudzbina = porudzbina;
    }

    public Long getRb() {
        return rb;
    }

    public void setRb(Long rb) {
        this.rb = rb;
    }

    public Double getIznos() {
        return iznos;
    }

    public void setIznos(Double iznos) {
        this.iznos = iznos;
    }

    public Double getCena() {
        return cena;
    }

    public void setCena(Double cena) {
        this.cena = cena;
    }

    public Long getKolicina() {
        return kolicina;
    }

    public void setKolicina(Long kolicina) {
        this.kolicina = kolicina;
    }

    public Kolac getKolac() {
        return kolac;
    }

    public void setKolac(Kolac kolac) {
        this.kolac = kolac;
    }
    
    
}
