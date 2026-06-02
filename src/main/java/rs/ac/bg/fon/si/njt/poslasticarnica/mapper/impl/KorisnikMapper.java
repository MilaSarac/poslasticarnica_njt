/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.si.njt.poslasticarnica.mapper.impl;

import org.springframework.stereotype.Component;
import rs.ac.bg.fon.si.njt.poslasticarnica.dto.impl.KorisnikDto;
import rs.ac.bg.fon.si.njt.poslasticarnica.entity.impl.Korisnik;
import rs.ac.bg.fon.si.njt.poslasticarnica.entity.impl.Mesto;
import rs.ac.bg.fon.si.njt.poslasticarnica.mapper.DtoEntityMapper;

/**
 *
 * @author Mila
 */

@Component
public class KorisnikMapper implements DtoEntityMapper<KorisnikDto, Korisnik> {

    @Override
    public KorisnikDto toDto(Korisnik e) {
        Long mestoId = e.getMesto() != null ? e.getMesto().getIdMesto() : null;
        return new KorisnikDto(e.getIdKorisnik(), e.getIme(), e.getPrezime(), e.getBrojTelefona(), e.getEmail(), mestoId);
    }

    @Override
    public Korisnik toEntity(KorisnikDto dto) {
        Mesto mesto = dto.getMestoId() != null ? 
                new Mesto(dto.getMestoId()) : null;
        return new Korisnik(
                dto.getIdKorisnik(),
                dto.getIme(),
                dto.getPrezime(),
                dto.getBrojTelefona(),
                dto.getEmail(),
                mesto);
    }
    
}
