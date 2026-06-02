/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.si.njt.poslasticarnica.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.bg.fon.si.njt.poslasticarnica.dto.impl.MestoDto;
import rs.ac.bg.fon.si.njt.poslasticarnica.entity.impl.Mesto;
import rs.ac.bg.fon.si.njt.poslasticarnica.mapper.impl.MestoMapper;
import rs.ac.bg.fon.si.njt.poslasticarnica.repository.impl.MestoRepository;

/**
 *
 * @author Mila
 */

@Service
//automatski registruje ovu klasu kao bean u app tacnije application context-u
//zato ne moramo da napisemo MestoService = new MestoService();
//mapiranje
public class MestoService {
    
    private final MestoRepository mestoRepository;
    private final MestoMapper mestoMapper;

    @Autowired
    //da napravi da i ove klase budu Bean-ovi
    public MestoService(MestoRepository mestoRepository, MestoMapper mestoMapper) {
        this.mestoRepository = mestoRepository;
        this.mestoMapper = mestoMapper;
    }
    
    //pretrava u dto ka controlleru
    //ucitava listu entity objekata
    //svaki entity mapira u dto
    //vraca listu dto objekata
    //mora da vrati Dto kontroleru
    //a repository mu daje entity
    public List<MestoDto> findAll(){
        return mestoRepository.findAll() //lista mesto entity-ja
                //pretvara listu u stream, stream api omogucava da nad celom listom 
                //ili nizom izvrsimo neke operacije u ansem slucaju .map i .collect
                .stream()
                //za svaku posl iz liste ce da pozove iz mestomappera toDto i vraca dto objekat
                .map(mestoMapper::toDto) 
                .collect(Collectors.toList()); //taj dto objkeat uz pomoc ove f-je se pakuje u listu
    }
    
    public MestoDto findById(Long id) throws Exception{
        return mestoMapper.toDto(mestoRepository.findById(id));
    }
    
    public MestoDto create(MestoDto mestoDto) {
        Mesto mesto = mestoMapper.toEntity(mestoDto);
        mestoRepository.save(mesto);
        return mestoMapper.toDto(mesto);
    }
    
    public void deleteById(Long id) {
        mestoRepository.deleteById(id);
    }
    
    public MestoDto update(MestoDto mestoDto) {
        Mesto updated = mestoMapper.toEntity(mestoDto);
        mestoRepository.save(updated);
        return mestoMapper.toDto(updated);
    }
}
