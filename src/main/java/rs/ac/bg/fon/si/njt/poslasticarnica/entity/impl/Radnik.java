/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.si.njt.poslasticarnica.entity.impl;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import rs.ac.bg.fon.si.njt.poslasticarnica.entity.DomainEntity;

/**
 *
 * @author Mila
 */

@Entity
@Table(name = "radnik")
public class Radnik implements DomainEntity{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRadnik;
    private String ime;
    private String prezime;
    private String username;
    private String password;

    public Radnik() {
    }
    
    public Radnik(Long idRadnik) {
        this.idRadnik = idRadnik;
    }

    public Radnik(Long idRadnik, String ime, String prezime, String username, String password) {
        this.idRadnik = idRadnik;
        this.ime = ime;
        this.prezime = prezime;
        this.username = username;
        this.password = password;
    }

    public Long getIdRadnik() {
        return idRadnik;
    }

    public void setIdRadnik(Long idRadnik) {
        this.idRadnik = idRadnik;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
}
