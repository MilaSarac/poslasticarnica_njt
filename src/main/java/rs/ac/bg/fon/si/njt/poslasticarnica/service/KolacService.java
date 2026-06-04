/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.si.njt.poslasticarnica.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.bg.fon.si.njt.poslasticarnica.dto.impl.KolacDto;
import rs.ac.bg.fon.si.njt.poslasticarnica.entity.impl.Kolac;
import rs.ac.bg.fon.si.njt.poslasticarnica.mapper.impl.KolacMapper;
import rs.ac.bg.fon.si.njt.poslasticarnica.repository.impl.KolacRepository;

/**
 *
 * @author Mila
 */

@Service
public class KolacService {
    
    private final KolacRepository kolacRepository;
    private final KolacMapper kolacMapper;

    @Autowired //automatski inicijalizuje objekte
    public KolacService(KolacRepository kolacRepository, KolacMapper kolacMapper) {
        this.kolacRepository = kolacRepository;
        this.kolacMapper = kolacMapper;
    }
    
    public List<KolacDto> findAll(){
        return kolacRepository.findAll() 
                .stream() //stream ce omoguciti da nad svakim objektom liste pozovemo map koja ce svaki obj pretvoriti u dto obj i nakon cega to vracamo u listu
                .map(kolacMapper::toDto) 
                .collect(Collectors.toList()); 
    }
    
    public KolacDto findById(Long id) throws Exception{
        return kolacMapper.toDto(kolacRepository.findById(id));
    }

    public KolacDto create(KolacDto kolacDto) {
        Kolac kolac = kolacMapper.toEntity(kolacDto);
        kolacRepository.save(kolac);
        return kolacMapper.toDto(kolac);
    }

    public void deleteById(Long id) {
        kolacRepository.deleteById(id);
    }
    
    public KolacDto update(KolacDto kolacDto) {
        Kolac updated = kolacMapper.toEntity(kolacDto);
        kolacRepository.save(updated);
        return kolacMapper.toDto(updated);
    }
}
