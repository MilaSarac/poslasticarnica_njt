/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.si.njt.poslasticarnica.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import rs.ac.bg.fon.si.njt.poslasticarnica.dto.impl.*;
import rs.ac.bg.fon.si.njt.poslasticarnica.entity.impl.*;
import rs.ac.bg.fon.si.njt.poslasticarnica.repository.impl.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import rs.ac.bg.fon.si.njt.poslasticarnica.service.AuthService;

/**
 *
 * @author Mila
 */

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/auth")
@Tag(name = "Auth")
public class AuthController {

    private final AuthService authService;
    private final KorisnikRepository korisnici;
    private final RadnikRepository radnici;

    public AuthController(AuthService authService, KorisnikRepository korisnici, RadnikRepository radnici) {
        this.authService = authService;
        this.korisnici = korisnici;
        this.radnici = radnici;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterRequest req) throws Exception {
        return ResponseEntity.ok(authService.register(req));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest req) {
        return ResponseEntity.ok(authService.login(req));
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout() {
        // JWT je stateless, logout se radi na klijentu (React obriše token iz localStorage)
        return ResponseEntity.ok().build();
    }

    @GetMapping("/me")
    public ResponseEntity<?> me(Authentication auth) {
        // auth.getName() nam daje username ulogovanog korisnika iz SecurityContext-a
        String username = auth.getName();

        // Pravimo mapu u koju ćemo "nabacati" podatke
        Map<String, Object> userDetails = new HashMap<>();

        // 1. Provera RADNIKA
        Optional<Radnik> r = radnici.findByUsername(username);
        if (r.isPresent()) {
            Radnik radnik = r.get();
            userDetails.put("id", radnik.getIdRadnik());
            userDetails.put("username", radnik.getUsername());
            userDetails.put("ime", radnik.getIme());
            userDetails.put("prezime", radnik.getPrezime());
            userDetails.put("role", "RADNIK");
            return ResponseEntity.ok(userDetails);
        }

        // 2. Provera KORISNIKA
        Optional<Korisnik> k = korisnici.findByUsername(username);
        if (k.isPresent()) {
            Korisnik korisnik = k.get();
            userDetails.put("id", korisnik.getIdKorisnik());
            userDetails.put("username", korisnik.getUsername());
            userDetails.put("ime", korisnik.getIme());
            userDetails.put("prezime", korisnik.getPrezime());
            userDetails.put("role", "KUPAC");
            return ResponseEntity.ok(userDetails);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Korisnik nije nađen");
    }
}
