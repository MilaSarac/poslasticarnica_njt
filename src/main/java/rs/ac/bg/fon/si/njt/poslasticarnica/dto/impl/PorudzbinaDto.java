/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.si.njt.poslasticarnica.dto.impl;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.Date;
import java.util.List;
import rs.ac.bg.fon.si.njt.poslasticarnica.dto.Dto;

/**
 *
 * @author Mila
 */
public class PorudzbinaDto implements Dto{
    
    private Long id;
    private Date datumKreiranja;
    private Double ukupanIznos;
    @NotNull(message = "Porudzbina mora imati klijenta")
    private Long korisnikId;
    @NotNull(message = "Porudzbina mora imati radnika")
    private Long radnikId;
    private List<StavkaPorudzbineDto> stavkePorudzbine;

    public PorudzbinaDto() {
    }

    public PorudzbinaDto(Long id, Date datumKreiranja, Double ukupanIznos, Long korisnikId, Long radnikId, List<StavkaPorudzbineDto> stavkePorudzbine) {
        this.id = id;
        this.datumKreiranja = datumKreiranja;
        this.ukupanIznos = ukupanIznos;
        this.korisnikId = korisnikId;
        this.radnikId = radnikId;
        this.stavkePorudzbine = stavkePorudzbine;
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

    public Long getKorisnikId() {
        return korisnikId;
    }

    public void setKorisnikId(Long korisnikId) {
        this.korisnikId = korisnikId;
    }

    public Long getRadnikId() {
        return radnikId;
    }

    public void setRadnikId(Long radnikId) {
        this.radnikId = radnikId;
    }

    public List<StavkaPorudzbineDto> getStavkePorudzbine() {
        return stavkePorudzbine;
    }

    public void setStavkePorudzbine(List<StavkaPorudzbineDto> stavkePorudzbine) {
        this.stavkePorudzbine = stavkePorudzbine;
    }
    
    

}
