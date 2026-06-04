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
        //password se nikad ne puni u dto iz entity
        if (e == null) return null;

        Long mestoId = e.getMesto() != null ? e.getMesto().getIdMesto() : null;
        
        KorisnikDto dto = new KorisnikDto();
        dto.setIdKorisnik(e.getIdKorisnik());
        dto.setIme(e.getIme());
        dto.setPrezime(e.getPrezime());
        dto.setBrojTelefona(e.getBrojTelefona());
        dto.setEmail(e.getEmail());
        dto.setUsername(e.getUsername());
        dto.setMestoId(mestoId);
        
        return dto;
    }

    @Override
    public Korisnik toEntity(KorisnikDto dto) {
        //password se postavlja u servisu posle encodera, ne ovde
        if (dto == null) return null;

        Mesto mesto = dto.getMestoId() != null ? new Mesto(dto.getMestoId()) : null;
        
        Korisnik k = new Korisnik();
        k.setIdKorisnik(dto.getIdKorisnik());
        k.setIme(dto.getIme());
        k.setPrezime(dto.getPrezime());
        k.setBrojTelefona(dto.getBrojTelefona());
        k.setEmail(dto.getEmail());
        k.setUsername(dto.getUsername());
        k.setMesto(mesto);
        
        // Password NE setujemo ovde, to ostaje servisu (BCrypt)
        
        return k;
    }
    
}
