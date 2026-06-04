/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.si.njt.poslasticarnica.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    
    public PorudzbinaDto create(PorudzbinaDto porudzbinaDto) {
        Porudzbina porudzbina = porudzbinaMapper.toEntity(porudzbinaDto);
        porudzbinaRepository.save(porudzbina);
        return porudzbinaMapper.toDto(porudzbina);
    }
    
    public void deleteById(Long id) {
        porudzbinaRepository.deleteById(id);
    }
    
    public PorudzbinaDto update(PorudzbinaDto porudzbinaDto) {
        Porudzbina updated = porudzbinaMapper.toEntity(porudzbinaDto);
        porudzbinaRepository.save(updated);
        return porudzbinaMapper.toDto(updated);
    }
}
