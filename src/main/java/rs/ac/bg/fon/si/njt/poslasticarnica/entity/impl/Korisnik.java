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
import rs.ac.bg.fon.si.njt.poslasticarnica.dto.Dto;

/**
 *
 * @author Mila
 */

@Entity
@Table(name = "korisnici")
public class Korisnik implements Dto{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idKorisnik;
    private String ime;
    private String prezime;
    private String brojTelefona;
    private String email;
    //status
    
    @ManyToOne
    @JoinColumn(name = "mesto_id")
    private Mesto mesto;

    public Korisnik() {
    }

    public Korisnik(Long idKorisnik) {
        this.idKorisnik = idKorisnik;
    }
    
    public Korisnik(Long idKorisnik, String ime, String prezime, String brojTelefona, String email, Mesto mesto) {
        this.idKorisnik = idKorisnik;
        this.ime = ime;
        this.prezime = prezime;
        this.brojTelefona = brojTelefona;
        this.email = email;
        this.mesto = mesto;
    }

    public Long getIdKorisnik() {
        return idKorisnik;
    }

    public void setIdKorisnik(Long idKorisnik) {
        this.idKorisnik = idKorisnik;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getBrojTelefona() {
        return brojTelefona;
    }

    public void setBrojTelefona(String brojTelefona) {
        this.brojTelefona = brojTelefona;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Mesto getMesto() {
        return mesto;
    }

    public void setMesto(Mesto mesto) {
        this.mesto = mesto;
    }
    
    
}
