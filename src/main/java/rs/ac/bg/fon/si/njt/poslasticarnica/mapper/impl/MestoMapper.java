/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.si.njt.poslasticarnica.mapper.impl;

import org.springframework.stereotype.Component;
import rs.ac.bg.fon.si.njt.poslasticarnica.dto.impl.MestoDto;
import rs.ac.bg.fon.si.njt.poslasticarnica.entity.impl.Mesto;
import rs.ac.bg.fon.si.njt.poslasticarnica.mapper.DtoEntityMapper;

/**
 *
 * @author Mila
 */

@Component
public class MestoMapper implements DtoEntityMapper<MestoDto, Mesto> {

    @Override
    public MestoDto toDto(Mesto e) {
        return new MestoDto(e.getIdMesto(), e.getNaziv());
    }

    @Override
    public Mesto toEntity(MestoDto dto) {
        return new Mesto(dto.getIdMesto(), dto.getNaziv());
    }
    
}
