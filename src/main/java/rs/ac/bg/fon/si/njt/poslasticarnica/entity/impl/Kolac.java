/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.si.njt.poslasticarnica.entity.impl;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "kolac")
public class Kolac implements DomainEntity{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String naziv;
    private String opis;
    private Double cenaPoKomadu;
    private String imageUrl;
    
    @Enumerated(EnumType.STRING)
    private VrstaKolaca vrsta;

    public Kolac() {
    }
    
    public Kolac(Long id) {
        this.id = id;
    }

    public Kolac(Long id, String naziv, String opis, Double cenaPoKomadu, String imageUrl, VrstaKolaca vrsta) {
        this.id = id;
        this.naziv = naziv;
        this.opis = opis;
        this.cenaPoKomadu = cenaPoKomadu;
        this.imageUrl = imageUrl;
        this.vrsta = vrsta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Double getCenaPoKomadu() {
        return cenaPoKomadu;
    }

    public void setCenaPoKomadu(Double cenaPoKomadu) {
        this.cenaPoKomadu = cenaPoKomadu;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public VrstaKolaca getVrsta() {
        return vrsta;
    }

    public void setVrsta(VrstaKolaca vrsta) {
        this.vrsta = vrsta;
    }
    
}
