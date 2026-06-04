/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.si.njt.poslasticarnica.mapper.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import rs.ac.bg.fon.si.njt.poslasticarnica.dto.impl.PorudzbinaDto;
import rs.ac.bg.fon.si.njt.poslasticarnica.dto.impl.StavkaPorudzbineDto;
import rs.ac.bg.fon.si.njt.poslasticarnica.entity.impl.Korisnik;
import rs.ac.bg.fon.si.njt.poslasticarnica.entity.impl.Porudzbina;
import rs.ac.bg.fon.si.njt.poslasticarnica.entity.impl.Radnik;
import rs.ac.bg.fon.si.njt.poslasticarnica.entity.impl.StavkaPorudzbine;
import rs.ac.bg.fon.si.njt.poslasticarnica.mapper.DtoEntityMapper;

/**
 *
 * @author Mila
 */

@Component
public class PorudzbinaMapper implements DtoEntityMapper<PorudzbinaDto, Porudzbina> {
    
    private final StavkaPorudzbineMapper stavkaMapper;

    public PorudzbinaMapper(StavkaPorudzbineMapper stavkaMapper) {
        this.stavkaMapper = stavkaMapper;
    }

    @Override
    public PorudzbinaDto toDto(Porudzbina e) {
        if (e == null) return null; 
        
        Long radnikId = e.getRadnik() != null ? e.getRadnik().getIdRadnik() : null;
        Long korisnikId = e.getKorisnik()!= null ? e.getKorisnik().getIdKorisnik(): null;
        
        List<StavkaPorudzbineDto> stavke = new ArrayList<>();
        if(e.getStavkePorudzbine()!=null) {
            stavke = e.getStavkePorudzbine()
                    .stream()
                    .map(stavkaMapper::toDto)
                    .collect(Collectors.toList());
        }
        
        PorudzbinaDto dto = new PorudzbinaDto();
        dto.setId(e.getId());
        dto.setDatumKreiranja(e.getDatumKreiranja());
        dto.setUkupanIznos(e.getUkupanIznos());
        dto.setKorisnikId(korisnikId);
        dto.setRadnikId(radnikId);
        dto.setStavkePorudzbine(stavke);
        
        return dto;
    }

    @Override
    public Porudzbina toEntity(PorudzbinaDto dto) {
        
        if (dto == null) return null;
        
        Radnik radnik = dto.getRadnikId() != null ? 
                new Radnik(dto.getRadnikId()) : null;
        Korisnik korisnik = dto.getKorisnikId() != null ? new Korisnik(dto.getKorisnikId()) : null;
        
        List<StavkaPorudzbine> stavke = new ArrayList<>();
        //Ovaj deo uzima listu svih stavki iz entiteta, svaku pojedinačnu stavku provlači kroz stavkaMapper 
        //da postane DTO, i sve ih pakuje u novu listu
        if(dto.getStavkePorudzbine()!=null) {
            stavke = dto.getStavkePorudzbine()
                    .stream()
                    .map(stavkaMapper::toEntity)
                    .collect(Collectors.toList());
        }
        /*if(t.getStavkePorudzbine()!=null){
            t.getStavkePorudzbine().forEach(d -> o.addItem(itemMapper.toEntity(d)));
        }*/
        
        Porudzbina p = new Porudzbina();
        p.setId(dto.getId());
        p.setDatumKreiranja(dto.getDatumKreiranja());
        p.setUkupanIznos(dto.getUkupanIznos());
        p.setKorisnik(korisnik);
        p.setRadnik(radnik);
        p.setStavkePorudzbine(stavke);
        
        return p;
    }
    
}
