/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.si.njt.poslasticarnica.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.bg.fon.si.njt.poslasticarnica.dto.impl.StavkaPorudzbineDto;
import rs.ac.bg.fon.si.njt.poslasticarnica.entity.impl.StavkaPorudzbine;
import rs.ac.bg.fon.si.njt.poslasticarnica.mapper.impl.StavkaPorudzbineMapper;
import rs.ac.bg.fon.si.njt.poslasticarnica.repository.impl.StavkaPorudzbineRepository;

/**
 *
 * @author Mila
 */

@Service
public class StavkaPorudzbineService {
    
    private final StavkaPorudzbineRepository spRepository;
    private final StavkaPorudzbineMapper spMapper;

    @Autowired
    public StavkaPorudzbineService(StavkaPorudzbineRepository spRepository, StavkaPorudzbineMapper spMapper) {
        this.spRepository = spRepository;
        this.spMapper = spMapper;
    }
    
    public List<StavkaPorudzbineDto> findAll(){
        return spRepository.findAll() 
                .stream()
                .map(spMapper::toDto) 
                .collect(Collectors.toList()); 
    }
    
    public StavkaPorudzbineDto create(StavkaPorudzbineDto spDto) {
        StavkaPorudzbine sp = spMapper.toEntity(spDto);
        spRepository.save(sp);
        return spMapper.toDto(sp);
    }
    
    public void deleteById(Long id) {
        spRepository.deleteById(id);
    }
    
    public StavkaPorudzbineDto update(StavkaPorudzbineDto spDto) {
        StavkaPorudzbine updated = spMapper.toEntity(spDto);
        spRepository.save(updated);
        return spMapper.toDto(updated);
    }
}
