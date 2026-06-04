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
import rs.ac.bg.fon.si.njt.poslasticarnica.dto.impl.StavkaPorudzbineDto;
import rs.ac.bg.fon.si.njt.poslasticarnica.service.StavkaPorudzbineService;

/**
 *
 * @author Mila
 */

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/stavka_porudzbine")
public class StavkaPorudzbineController {
    
    private final StavkaPorudzbineService spService;

    public StavkaPorudzbineController(StavkaPorudzbineService spService) {
        this.spService = spService;
    }
    
    @GetMapping
    @Operation(summary = "Retrieve all Stavka porudzbine entities.")
    public ResponseEntity<List<StavkaPorudzbineDto>> getAll(){
        return new ResponseEntity<>(spService.findAll(),
                HttpStatus.OK);
    }
    
    @PostMapping
    @Operation(summary = "Create a new Stavka porudzbine entities.")
    public ResponseEntity<StavkaPorudzbineDto> addStavkaPorudzbine(
            @Valid @RequestBody StavkaPorudzbineDto spDto){
        try {
        StavkaPorudzbineDto saved =spService.create(spDto);
        return new ResponseEntity<>(saved,HttpStatus.CREATED);
        } catch (Exception ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, 
                    "Error while saving stavka porudzbine: "+ex.getMessage());
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable(value = "id") Long id){
        try{
            spService.deleteById(id);
            return new ResponseEntity<>("Stavka porudzbine successfully deleted.", HttpStatus.OK);
        } catch (Exception ex){
            return new ResponseEntity<>("Stavka porudzbine does not exist: "+id, HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Update an existing Stavka porudzbine entities.")
    public ResponseEntity<StavkaPorudzbineDto> updateStavkaPorudzbine(
            @PathVariable Long id, 
            @Valid @RequestBody StavkaPorudzbineDto spDto){ 
        try {
            spDto.setRb(id);
            StavkaPorudzbineDto updated = spService.update(spDto);
        return new ResponseEntity<>(updated,HttpStatus.OK);
        } catch (Exception ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, 
                    "Error while updating stavka porudzbine: "+ex.getMessage());
        }
    }
}
