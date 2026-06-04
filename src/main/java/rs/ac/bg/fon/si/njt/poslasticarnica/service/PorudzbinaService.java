/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.si.njt.poslasticarnica.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.ac.bg.fon.si.njt.poslasticarnica.dto.impl.PorudzbinaDto;
import rs.ac.bg.fon.si.njt.poslasticarnica.entity.impl.Porudzbina;
import rs.ac.bg.fon.si.njt.poslasticarnica.mapper.impl.PorudzbinaMapper;
import rs.ac.bg.fon.si.njt.poslasticarnica.repository.impl.PorudzbinaRepository;

/**
 *
 * @author Mila
 */

@Service
public class PorudzbinaService {
    
    private final PorudzbinaRepository porudzbinaRepository;
    private final PorudzbinaMapper porudzbinaMapper;
    
    // Dependency Injection preko konstruktora
    @Autowired
    public PorudzbinaService(PorudzbinaRepository porudzbinaRepository, PorudzbinaMapper porudzbinaMapper) {
        this.porudzbinaRepository = porudzbinaRepository;
        this.porudzbinaMapper = porudzbinaMapper;
    }
    
    public List<PorudzbinaDto> findAll(){
        return porudzbinaRepository.findAll() 
                .stream()
                .map(porudzbinaMapper::toDto) 
                .collect(Collectors.toList()); 
    }
    
    //SK12
    @Transactional
    public PorudzbinaDto create(PorudzbinaDto porudzbinaDto) {
        Porudzbina porudzbina = porudzbinaMapper.toEntity(porudzbinaDto);
        if (porudzbina.getDatumKreiranja() == null) {
            porudzbina.setDatumKreiranja(new Date()); // Postavlja trenutno vreme
        }
        porudzbinaRepository.save(porudzbina);
        return porudzbinaMapper.toDto(porudzbina);
    }
    
    //SK13
    public void deleteById(Long id) {
        /*if (!porudzbinaRepository.existsById(id)) {
            throw new EntityNotFoundException("Porudžbina sa ID-em " + id + " ne postoji.");
        }*/
        porudzbinaRepository.deleteById(id);
    }
    
    public PorudzbinaDto update(PorudzbinaDto porudzbinaDto) {
        Porudzbina updated = porudzbinaMapper.toEntity(porudzbinaDto);
        porudzbinaRepository.save(updated);
        return porudzbinaMapper.toDto(updated);
    }
}
