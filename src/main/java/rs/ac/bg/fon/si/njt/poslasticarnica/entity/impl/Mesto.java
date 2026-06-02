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
@Table(name = "mesto")
public class Mesto implements DomainEntity{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMesto;
    private String naziv;

    public Mesto() {
    }
    
    public Mesto(Long idMesto) {
        this.idMesto = idMesto;
        
    }

    public Mesto(Long idMesto, String naziv) {
        this.idMesto = idMesto;
        this.naziv = naziv;
    }

    public Long getIdMesto() {
        return idMesto;
    }

    public void setIdMesto(Long idMesto) {
        this.idMesto = idMesto;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    
}
