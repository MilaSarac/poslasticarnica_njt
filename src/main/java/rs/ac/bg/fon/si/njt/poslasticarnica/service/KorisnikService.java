/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.si.njt.poslasticarnica.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.bg.fon.si.njt.poslasticarnica.dto.impl.KorisnikDto;
import rs.ac.bg.fon.si.njt.poslasticarnica.entity.impl.Korisnik;
import rs.ac.bg.fon.si.njt.poslasticarnica.mapper.impl.KorisnikMapper;
import rs.ac.bg.fon.si.njt.poslasticarnica.repository.impl.KorisnikRepository;

/**
 *
 * @author Mila
 */

@Service
public class KorisnikService {
    
    private final KorisnikRepository korisnikRepository;
    private final KorisnikMapper korisnikMapper;

    @Autowired
    public KorisnikService(KorisnikRepository korisnikRepository, KorisnikMapper korisnikMapper) {
        this.korisnikRepository = korisnikRepository;
        this.korisnikMapper = korisnikMapper;
    }
    
    public List<KorisnikDto> findAll(){
        return korisnikRepository.findAll()
                .stream()
                .map(korisnikMapper::toDto) 
                .collect(Collectors.toList());
    }

    public KorisnikDto create(KorisnikDto korisnikDto) {
        Korisnik korisnik = korisnikMapper.toEntity(korisnikDto);
        korisnikRepository.save(korisnik);
        return korisnikMapper.toDto(korisnik);
    }
    
    public void deleteById(Long id) {
        korisnikRepository.deleteById(id);
    }

    public KorisnikDto update(KorisnikDto korisnikDto) {
        Korisnik updated = korisnikMapper.toEntity(korisnikDto);
        korisnikRepository.save(updated);
        return korisnikMapper.toDto(updated);
    }
    
    
}
