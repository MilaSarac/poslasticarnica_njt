/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.si.njt.poslasticarnica.dto.impl;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/**
 *
 * @author Mila
 */


public class StavkaPorudzbineDto {
    
    private Long rb;
    @NotNull(message = "Ukupan iznos je obavezan.")
    @Positive(message = "Ukupan iznos mora biti veći od nule.")
    private Double iznos;
    @NotNull(message = "Cena je obavezna.")
    private Double cena;
    @Min(value=1,message = "Kolicina mora biti bar 1")
    private Long kolicina;

    private Long porudzbinaId;
    
    private Long kolacId;

    public StavkaPorudzbineDto() {
    }

    public StavkaPorudzbineDto(Long rb, Double iznos, Double cena, Long kolicina, Long porudzbinaId, Long kolacId) {
        this.rb = rb;
        this.iznos = iznos;
        this.cena = cena;
        this.kolicina = kolicina;
        this.porudzbinaId = porudzbinaId;
        this.kolacId = kolacId;
    }

    public Long getPorudzbinaId() {
        return porudzbinaId;
    }

    public void setPorudzbinaId(Long porudzbinaId) {
        this.porudzbinaId = porudzbinaId;
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

    public Long getKolacId() {
        return kolacId;
    }

    public void setKolacId(Long kolacId) {
        this.kolacId = kolacId;
    }

    
}
