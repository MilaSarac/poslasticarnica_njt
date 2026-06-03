/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.si.njt.poslasticarnica.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import rs.ac.bg.fon.si.njt.poslasticarnica.dto.impl.KorisnikDto;
import rs.ac.bg.fon.si.njt.poslasticarnica.service.KorisnikService;

/**
 *
 * @author Mila
 */

@RestController
@RequestMapping("/api/korisnik")
public class KorisnikController {
    
    private final KorisnikService korisnikService;

    public KorisnikController(KorisnikService korisnikService) {
        this.korisnikService = korisnikService;
    }
    
    @GetMapping
    @Operation(summary = "Retrieve all Korisnik entities.")
    public ResponseEntity<List<KorisnikDto>> getAll(){
        return new ResponseEntity<>(korisnikService.findAll(),
                HttpStatus.OK);
    }
    
    @PostMapping
    @Operation(summary = "Create a new Korisnik entities.")
    public ResponseEntity<KorisnikDto> addKorisnik(
            @Valid @RequestBody KorisnikDto korisnikDto){
        try {
        KorisnikDto saved =korisnikService.create(korisnikDto);
        return new ResponseEntity<>(saved,HttpStatus.CREATED);
        } catch (Exception ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, 
                    "Error while saving korisnik: "+ex.getMessage());
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable(value = "id") Long id){
        try{
            korisnikService.deleteById(id);
            return new ResponseEntity<>("Korisnik successfully deleted.", HttpStatus.OK);
        } catch (Exception ex){
            return new ResponseEntity<>("Korisnik does not exist: "+id, HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Update an existing Korisnik entities.")
    public ResponseEntity<KorisnikDto> updateKorisnik(
            @PathVariable Long id, 
            @Valid @RequestBody KorisnikDto korisnikDto){ 
        try {
            korisnikDto.setIdKorisnik(id); 
            KorisnikDto updated = korisnikService.update(korisnikDto);
        return new ResponseEntity<>(updated,HttpStatus.OK);
        } catch (Exception ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, 
                    "Error while updating korisnik: "+ex.getMessage());
        }
    }
}
