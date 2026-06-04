/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.si.njt.poslasticarnica.dto.impl;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;
import rs.ac.bg.fon.si.njt.poslasticarnica.dto.Dto;
import rs.ac.bg.fon.si.njt.poslasticarnica.entity.impl.VrstaKolaca;

/**
 *
 * @author Mila
 */
public class KolacDto implements Dto{
    
    //VALIDACIJA
    
    private Long id;
    @NotEmpty(message = "Name iz required.")
    @Size(min=2, max=100, message = "Name must be between longer 2 and 100 characters.")
    private String naziv;
    @Size(max=500, message = "Description can be at most 500 characters.")
    private String opis;
    @NotNull(message = "Price is required.")
    @Positive(message = "Price must be greater than zero.")
    private Double cenaPoKomadu;
    @URL(message = "Image URL must be valid.")
    private String imageUrl; //cuva se kao link
    
    @NotNull(message = "Type of cake is required.")
    private VrstaKolaca vrsta;

    public KolacDto() {
    }

    public KolacDto(Long id, String naziv, String opis, Double cenaPoKomadu, String imageUrl, VrstaKolaca vrsta) {
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
