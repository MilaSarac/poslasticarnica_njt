/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.si.njt.poslasticarnica.dto.impl;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import rs.ac.bg.fon.si.njt.poslasticarnica.dto.Dto;

/**
 *
 * @author Mila
 */
public class MestoDto implements Dto{
    
    private Long idMesto;
    @NotEmpty(message = "Naziv iz required.")
    @Size(min=2, max=100, message = "Name must be between longer 2 and 100 characters.")
    private String naziv;

    public MestoDto() {
    }

    public MestoDto(Long idMesto, String naziv) {
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
