/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.si.njt.poslasticarnica.mapper.impl;

import rs.ac.bg.fon.si.njt.poslasticarnica.dto.impl.StavkaPorudzbineDto;
import rs.ac.bg.fon.si.njt.poslasticarnica.entity.impl.Kolac;
import rs.ac.bg.fon.si.njt.poslasticarnica.entity.impl.Porudzbina;
import rs.ac.bg.fon.si.njt.poslasticarnica.entity.impl.StavkaPorudzbine;
import rs.ac.bg.fon.si.njt.poslasticarnica.mapper.DtoEntityMapper;

/**
 *
 * @author Mila
 */
public class StavkaPorudzbineMapper implements DtoEntityMapper<StavkaPorudzbineDto, StavkaPorudzbine> {

    @Override
    public StavkaPorudzbineDto toDto(StavkaPorudzbine e) {
        Long porudzbinaId = e.getPorudzbina() != null ? e.getPorudzbina().getId() : null;
        Long kolacId = e.getKolac() != null ? e.getKolac().getId() : null;
        return new StavkaPorudzbineDto(
                e.getRb(),
                e.getIznos(),
                e.getCena(),
                e.getKolicina(),
                porudzbinaId,
                kolacId);
    }

    @Override
    public StavkaPorudzbine toEntity(StavkaPorudzbineDto dto) {
        Porudzbina porudzbina = dto.getPorudzbinaId() != null ? 
                new Porudzbina(dto.getPorudzbinaId()) : null;
        Kolac kolac = dto.getKolacId() != null ? new Kolac(dto.getKolacId()) : null;
        return new StavkaPorudzbine(
                dto.getRb(),
                dto.getIznos(),
                dto.getCena(),
                dto.getKolicina(),
                porudzbina,
                kolac);
    }
    
}
