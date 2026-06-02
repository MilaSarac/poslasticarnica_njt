/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.si.njt.poslasticarnica.mapper.impl;

import org.springframework.stereotype.Component;
import rs.ac.bg.fon.si.njt.poslasticarnica.dto.impl.RadnikDto;
import rs.ac.bg.fon.si.njt.poslasticarnica.entity.impl.Radnik;
import rs.ac.bg.fon.si.njt.poslasticarnica.mapper.DtoEntityMapper;

/**
 *
 * @author Mila
 */

@Component
public class RadnikMapper implements DtoEntityMapper<RadnikDto, Radnik> {

    @Override
    public RadnikDto toDto(Radnik e) {
        return new RadnikDto(e.getIdRadnik(), e.getIme(), e.getPrezime(), e.getUsername(), e.getPassword());
    }

    @Override
    public Radnik toEntity(RadnikDto dto) {
        return new Radnik(
                dto.getIdRadnik(), 
                dto.getIme(), 
                dto.getPrezime(), 
                dto.getUsername(), 
                dto.getPassword());
    }
    
}
