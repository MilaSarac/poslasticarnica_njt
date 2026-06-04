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
import rs.ac.bg.fon.si.njt.poslasticarnica.dto.impl.RadnikDto;
import rs.ac.bg.fon.si.njt.poslasticarnica.service.RadnikService;

/**
 *
 * @author Mila
 */

@RestController
@RequestMapping("/api/radnik")
public class RadnikController {
    
    private final RadnikService radnikService;

    public RadnikController(RadnikService radnikService) {
        this.radnikService = radnikService;
    }
    
    @GetMapping
    @Operation(summary = "Retrieve all Radnik entities.")
    public ResponseEntity<List<RadnikDto>> getAll(){
        return new ResponseEntity<>(radnikService.findAll(),
                HttpStatus.OK);
    }
    
    @PostMapping
    @Operation(summary = "Create a new Radnik entities.")
    public ResponseEntity<RadnikDto> addRadnik(
            @Valid @RequestBody RadnikDto radnikDto){
        try {
        RadnikDto saved =radnikService.create(radnikDto);
        return new ResponseEntity<>(saved,HttpStatus.CREATED);
        } catch (Exception ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, 
                    "Error while saving radnik: "+ex.getMessage());
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable(value = "id") Long id){
        try{
            radnikService.deleteById(id);
            return new ResponseEntity<>("Radnik successfully deleted.", HttpStatus.OK);
        } catch (Exception ex){
            return new ResponseEntity<>("Radnik does not exist: "+id, HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Update an existing Radnik entities.")
    public ResponseEntity<RadnikDto> updateRadnik(
            @PathVariable Long id, 
            @Valid @RequestBody RadnikDto radnikDto){ 
        try {
            radnikDto.setIdRadnik(id); 
            RadnikDto updated = radnikService.update(radnikDto);
        return new ResponseEntity<>(updated,HttpStatus.OK);
        } catch (Exception ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, 
                    "Error while updating radnik: "+ex.getMessage());
        }
    }
}
