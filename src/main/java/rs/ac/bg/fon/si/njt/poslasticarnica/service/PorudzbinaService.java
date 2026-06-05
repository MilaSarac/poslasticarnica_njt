/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.si.njt.poslasticarnica.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.ac.bg.fon.si.njt.poslasticarnica.dto.impl.PorudzbinaDto;
import rs.ac.bg.fon.si.njt.poslasticarnica.dto.impl.StavkaPorudzbineDto;
import rs.ac.bg.fon.si.njt.poslasticarnica.entity.impl.Kolac;
import rs.ac.bg.fon.si.njt.poslasticarnica.entity.impl.Korisnik;
import rs.ac.bg.fon.si.njt.poslasticarnica.entity.impl.Porudzbina;
import rs.ac.bg.fon.si.njt.poslasticarnica.entity.impl.Radnik;
import rs.ac.bg.fon.si.njt.poslasticarnica.entity.impl.StavkaPorudzbine;
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
    
    @PersistenceContext
    private EntityManager em;
    
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
    //Kreira porudzbinu i sve njene stavke u jednoj transakciji
    public PorudzbinaDto create(PorudzbinaDto porudzbinaDto) throws Exception {
        //Porudzbina porudzbina = porudzbinaMapper.toEntity(porudzbinaDto);
        
        //Kreira se objekat
        Porudzbina porudzbina = new Porudzbina();
        
        //Povezuje se sa korisnikom (lazy referenca)
        if(porudzbinaDto.getKorisnikId() == null){
            throw new Exception("korisnikId is required");
        }
        porudzbina.setKorisnik(em.getReference(
                Korisnik.class, porudzbinaDto.getKorisnikId()));
        
        //Povezuje se sa radnikom
        if(porudzbinaDto.getRadnikId() == null){
            throw new Exception("radnikId is required");
        }
        porudzbina.setRadnik(em.getReference(
                Radnik.class, porudzbinaDto.getRadnikId()));
        
        if (porudzbina.getDatumKreiranja() == null) {
            porudzbina.setDatumKreiranja(new Date()); // Postavlja trenutno vreme
        }
        
        if(porudzbinaDto.getStavkePorudzbine() == null 
                || porudzbinaDto.getStavkePorudzbine().isEmpty()){
            throw new Exception("Porudzbina mora da sadrzi najmanje jednu stavku.");
        }
        
        double ukupno = 0;
        for(StavkaPorudzbineDto spt : porudzbinaDto.getStavkePorudzbine()){
            StavkaPorudzbine sp = new StavkaPorudzbine();
            Kolac k = em.getReference(Kolac.class, spt.getKolacId());
            sp.setKolac(k);
            sp.setKolicina(spt.getKolicina());
            //double cena= spt.getCena() > 0 ? spt.getCena() : k.getCenaPoKomadu();
            sp.setCena(k.getCenaPoKomadu());
            double iznosStavke = k.getCenaPoKomadu() * spt.getKolicina();
            sp.setIznos(iznosStavke);
            ukupno += iznosStavke;
            sp.setPorudzbina(porudzbina);
            porudzbina.addStavka(sp);
        }
        porudzbina.setUkupanIznos(ukupno);
        porudzbinaRepository.save(porudzbina);
        return porudzbinaMapper.toDto(porudzbina);
    }
    
    //SK13
    //Brisanje porudzbinu i sve njene stavke u jednoj transakciji
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
