/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.si.njt.poslasticarnica.mapper.impl;

import org.springframework.stereotype.Component;
import rs.ac.bg.fon.si.njt.poslasticarnica.dto.impl.StavkaPorudzbineDto;
import rs.ac.bg.fon.si.njt.poslasticarnica.entity.impl.Kolac;
import rs.ac.bg.fon.si.njt.poslasticarnica.entity.impl.Porudzbina;
import rs.ac.bg.fon.si.njt.poslasticarnica.entity.impl.StavkaPorudzbine;
import rs.ac.bg.fon.si.njt.poslasticarnica.mapper.DtoEntityMapper;

/**
 *
 * @author Mila
 */

@Component
public class StavkaPorudzbineMapper implements DtoEntityMapper<StavkaPorudzbineDto, StavkaPorudzbine> {

    @Override
    public StavkaPorudzbineDto toDto(StavkaPorudzbine e) {
        if (e == null) return null;
                
        Long porudzbinaId = e.getPorudzbina() != null ? e.getPorudzbina().getId() : null;
        Long kolacId = e.getKolac() != null ? e.getKolac().getId() : null;
        
        StavkaPorudzbineDto dto = new StavkaPorudzbineDto();
        dto.setRb(e.getRb());
        dto.setIznos(e.getIznos());
        dto.setCena(e.getCena());
        dto.setKolicina(e.getKolicina());
        dto.setKolacId(kolacId);
        dto.setPorudzbinaId(porudzbinaId);
        
        return dto;
    }

    @Override
    public StavkaPorudzbine toEntity(StavkaPorudzbineDto dto) {
        if (dto == null) return null;
        
        Porudzbina porudzbina = dto.getPorudzbinaId() != null ? 
                new Porudzbina(dto.getPorudzbinaId()) : null;
        Kolac kolac = dto.getKolacId() != null ? new Kolac(dto.getKolacId()) : null;

        StavkaPorudzbine s = new StavkaPorudzbine();
        s.setRb(dto.getRb());
        s.setIznos(dto.getIznos());
        s.setCena(dto.getCena());
        s.setKolicina(dto.getKolicina());
        s.setPorudzbina(porudzbina);
        s.setKolac(kolac);
        
        return s;
    }
    
}
