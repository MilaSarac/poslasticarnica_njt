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
        if (e == null) return null;
        // Kreiramo DTO objekat i popunjavamo ga seterima
        RadnikDto dto = new RadnikDto();
        dto.setIdRadnik(e.getIdRadnik());
        dto.setIme(e.getIme());
        dto.setPrezime(e.getPrezime());
        dto.setUsername(e.getUsername());
        // Lozinka se NIKADA ne šalje u DTO

        return dto;
    }

    @Override
    public Radnik toEntity(RadnikDto dto) {
        if (dto == null) return null;

        // Kreiramo ENITET objekat i popunjavamo ga seterima
        Radnik entity = new Radnik(); // Poziva se default konstruktor
        entity.setIdRadnik(dto.getIdRadnik());
        entity.setIme(dto.getIme());
        entity.setPrezime(dto.getPrezime());
        entity.setUsername(dto.getUsername());
        // Lozinka se NE setuje ovde, jer ona dolazi iz servisa nakon enkripcije

        return entity;
    }
    
}
