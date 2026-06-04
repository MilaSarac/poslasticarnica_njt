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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import rs.ac.bg.fon.si.njt.poslasticarnica.dto.impl.PorudzbinaDto;
import rs.ac.bg.fon.si.njt.poslasticarnica.service.PorudzbinaService;

/**
 *
 * @author Mila
 */

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/porudzbina")
public class PorudzbinaController {
    
    private final PorudzbinaService porudzbinaService;

    public PorudzbinaController(PorudzbinaService porudzbinaService) {
        this.porudzbinaService = porudzbinaService;
    }

    @GetMapping
    @Operation(summary = "Retrieve all Porudzbina entities.")
    public ResponseEntity<List<PorudzbinaDto>> getAll(){
        return new ResponseEntity<>(porudzbinaService.findAll(),
                HttpStatus.OK);
    }
    
    @PostMapping
    @Operation(summary = "Create a new Porudzbina entities.")
    public ResponseEntity<PorudzbinaDto> addPorudzbina(
            @Valid @RequestBody PorudzbinaDto porudzbinaDto){
        try {
        PorudzbinaDto saved =porudzbinaService.create(porudzbinaDto);
        return new ResponseEntity<>(saved,HttpStatus.CREATED);
        } catch (Exception ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, 
                    "Error while saving porudzbina: "+ex.getMessage());
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable(value = "id") Long id){
        try{
            porudzbinaService.deleteById(id);
            return new ResponseEntity<>("Porudzbina successfully deleted.", HttpStatus.OK);
        } catch (Exception ex){
            return new ResponseEntity<>("Porudzbina does not exist: "+id, HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Update an existing Porudzbina entities.")
    public ResponseEntity<PorudzbinaDto> updatePorudzbina(
            @PathVariable Long id, 
            @Valid @RequestBody PorudzbinaDto porudzbinaDto){ 
        try {
            porudzbinaDto.setId(id); 
            PorudzbinaDto updated = porudzbinaService.update(porudzbinaDto);
        return new ResponseEntity<>(updated,HttpStatus.OK);
        } catch (Exception ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, 
                    "Error while updating porudzbina: "+ex.getMessage());
        }
    }
}
