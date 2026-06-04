/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.si.njt.poslasticarnica.mapper.impl;

import org.springframework.stereotype.Component;
import rs.ac.bg.fon.si.njt.poslasticarnica.dto.impl.KolacDto;
import rs.ac.bg.fon.si.njt.poslasticarnica.entity.impl.Kolac;
import rs.ac.bg.fon.si.njt.poslasticarnica.mapper.DtoEntityMapper;

/**
 *
 * @author Mila
 */

@Component
public class KolacMapper implements DtoEntityMapper<KolacDto, Kolac> {

    @Override
    public KolacDto toDto(Kolac e) {
        // PROVERA: Ako je entitet null, vrati null umesto da pozivaš getere
        if (e == null) {
            return null;
        }
        
        return new KolacDto(
                e.getId(), 
                e.getNaziv(), 
                e.getOpis(), 
                e.getCenaPoKomadu(), 
                e.getImageUrl(), 
                e.getVrsta()
        );
    }

    @Override
    public Kolac toEntity(KolacDto dto) {
        // PROVERA: Ako je DTO null, vrati null
        if (dto == null) {
            return null;
        }

        return new Kolac(
                dto.getId(), 
                dto.getNaziv(), 
                dto.getOpis(), 
                dto.getCenaPoKomadu(), 
                dto.getImageUrl(), 
                dto.getVrsta()
        );
    }
    
}
