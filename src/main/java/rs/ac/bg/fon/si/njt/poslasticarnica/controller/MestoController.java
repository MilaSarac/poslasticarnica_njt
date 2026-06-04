/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.si.njt.poslasticarnica.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
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
import rs.ac.bg.fon.si.njt.poslasticarnica.dto.impl.MestoDto;
import rs.ac.bg.fon.si.njt.poslasticarnica.service.MestoService;

/**
 *
 * @author Mila
 */

@CrossOrigin(origins = "http://localhost:3000")
@RestController
//omogucava CRUD op.
//svaki http zahtev koji dolazi sa klijenta na serversku stranu izmedju ostalih parametara ima link
//kako controller zna za koje linkove je on zaduzen?
@RequestMapping("/api/mesto")
//svi zahtevi koji dodju proveri da li zapocinju sa ovim, ako zapocinju onda je ovaj controller zaduzen za to
public class MestoController {
    
    private final MestoService mestoService;

    public MestoController(MestoService mestoService) {
        this.mestoService = mestoService;
    }
    
    @GetMapping //("/{id}")
    //tip metode
    //objasnjenje sta metoda radi, korisno za generisanje automatske dokumentacije
    @Operation(summary = "Retrieve all Mesto entities.")
    //dodatne info, content je heder
    @ApiResponse(responseCode = "200", content = {
        @Content(schema = @Schema(
                implementation = MestoDto.class), 
                mediaType = "application/json")
    })
    //server vraca klijentu, u sebi ima listu MestoDto objekata
    public ResponseEntity<List<MestoDto>> getAll(){
        return new ResponseEntity<>(mestoService.findAll(),
                HttpStatus.OK);
    }
    
    //ne mozemo da imamo dve metode koje imaju istu rutu
    @GetMapping("/{id}")
    public ResponseEntity<MestoDto> getById(
        @NotNull(message = "Should not be null or empty.")
        @PathVariable(value = "id") Long id) {
        //prima parametar Long id koji ima anotaciju da ne sme da bude null i da ce se taj parametar
        //zvati id, zapravo je to iz naseg linka taj id
        try {
            return new ResponseEntity<>(mestoService.findById(id),
                HttpStatus.OK);
        } catch (Exception ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, 
                    "MestoController exception: "+ex.getMessage());
        }
    }
    
    @PostMapping
    @Operation(summary = "Create a new Mesto entities.")
    @ApiResponse(responseCode = "201", content = {
        @Content(schema = @Schema(
                implementation = MestoDto.class), 
                mediaType = "application/json")
    })
    public ResponseEntity<MestoDto> addMesto(
            @Valid @RequestBody @NotNull MestoDto mestoDto){
        //svaki http zahtev ima header link metodu body...
        //iz body parametra treba da izvucemo podatke o mestu koje treba da kreiramo
        //prvo validiramo body param, izvlacimo body iz celog reaquesta ne sme da bude null 
        //i kroz body param cemo poslati json obj gde ce biti podaci o mestu
        //i on ce automatski znaci da treba da ga prevede u dto obj
        try {
        System.out.println(mestoDto);
        MestoDto saved = mestoService.create(mestoDto);
        return new ResponseEntity<>(saved,HttpStatus.CREATED);
        } catch (Exception ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, 
                    "Error while saving mesto: "+ex.getMessage());
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable(value = "id") Long id){
        try{
            mestoService.deleteById(id);
            return new ResponseEntity<>("Mesto successfully deleted.", HttpStatus.OK);
        } catch (Exception ex){
            return new ResponseEntity<>("Mesto does not exist: "+id, HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Update an existing Mesto entities.")
    @ApiResponse(responseCode = "200", content = {
        @Content(schema = @Schema(
                implementation = MestoDto.class), 
                mediaType = "application/json")
    })
    public ResponseEntity<MestoDto> updateMesto(
            @PathVariable Long id, //izvlaci id iz linka
            @Valid @RequestBody MestoDto mestoDto){ //uzima body validira i pretvori u mestoDto obj
        try {
            mestoDto.setIdMesto(id); //Osiguramo da se azurira pravi entitet
            MestoDto updated = mestoService.update(mestoDto);
        return new ResponseEntity<>(updated,HttpStatus.OK);
        } catch (Exception ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, 
                    "Error while updating mesto: "+ex.getMessage());
        }
    }
}
