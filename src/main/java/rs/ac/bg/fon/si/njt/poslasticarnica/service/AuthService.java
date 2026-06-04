/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.si.njt.poslasticarnica.service;

import org.springframework.stereotype.Service;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import rs.ac.bg.fon.si.njt.poslasticarnica.security.JwtService;
import java.util.Map;
import java.util.Optional;
import rs.ac.bg.fon.si.njt.poslasticarnica.repository.impl.*;
import rs.ac.bg.fon.si.njt.poslasticarnica.entity.impl.*;
import rs.ac.bg.fon.si.njt.poslasticarnica.dto.impl.*;

/**
 *
 * @author Mila
 */

@Service
public class AuthService {

    private final AuthenticationManager authManager;
    private final JwtService jwt;
    private final KorisnikRepository korisnici;
    private final RadnikRepository radnici;
    private final PasswordEncoder encoder;
    private final MestoRepository mestoRepo; // Treba nam za registraciju kupca

    public AuthService(AuthenticationManager authManager, JwtService jwt, 
                       KorisnikRepository korisnici, RadnikRepository radnici, 
                       PasswordEncoder encoder, MestoRepository mestoRepo) {
        this.authManager = authManager;
        this.jwt = jwt;
        this.korisnici = korisnici;
        this.radnici = radnici;
        this.encoder = encoder;
        this.mestoRepo = mestoRepo;
    }

    public AuthResponse login(LoginRequest req) {
        // 1. Autentifikacija preko Spring Security-ja
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword())
        );

        // 2. Ako ne baci Exception, autentifikacija je prošla. Uzimamo ulogu.
        User springUser = (User) auth.getPrincipal();
        String role = springUser.getAuthorities().iterator().next().getAuthority();

        // 3. Generisanje tokena (prosleđujemo rolu kao "extra claims" kao na slici)
        String token = jwt.generate(springUser, Map.of("role", role));

        // 4. Pronalaženje ID-a korisnika ili radnika za AuthResponse
        Long id;
        Role finalRole;
        if (role.equals("RADNIK")) {
            id = radnici.findByUsername(req.getUsername()).get().getIdRadnik();
            finalRole = Role.RADNIK;
        } else {
            id = korisnici.findByUsername(req.getUsername()).get().getIdKorisnik();
            finalRole = Role.KUPAC;
        }
        
        // Sada prosleđujemo finalRole koji je tipa Role
        return new AuthResponse(req.getUsername(), finalRole, token, id);
    }

    public String register(RegisterRequest req) throws Exception {
        // Provera da li korisničko ime već postoji u bilo kojoj tabeli
        if (korisnici.findByUsername(req.getUsername()).isPresent() || 
            radnici.findByUsername(req.getUsername()).isPresent()) {
            throw new Exception("Username already taken");
        }

        // Kreiranje novog korisnika (Kupca)
        Korisnik k = new Korisnik();
        k.setIme(req.getIme());
        k.setPrezime(req.getPrezime());
        k.setUsername(req.getUsername());
        k.setEmail(req.getEmail());
        k.setBrojTelefona(req.getBrojTelefona());
        
        // Enkodiranje lozinke (kao na slici)
        k.setPassword(encoder.encode(req.getPassword()));

        // Postavljanje mesta
        if (req.getMestoId() != null) {
            Mesto m = mestoRepo.findById(req.getMestoId());
            k.setMesto(m);
        }

        korisnici.save(k);
        return "Registration successful";
    }
}
