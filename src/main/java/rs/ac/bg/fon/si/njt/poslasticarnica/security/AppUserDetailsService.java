/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.si.njt.poslasticarnica.security;


import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import rs.ac.bg.fon.si.njt.poslasticarnica.entity.impl.Korisnik;
import rs.ac.bg.fon.si.njt.poslasticarnica.entity.impl.Radnik;
import rs.ac.bg.fon.si.njt.poslasticarnica.repository.impl.KorisnikRepository;
import rs.ac.bg.fon.si.njt.poslasticarnica.repository.impl.RadnikRepository;

/**
 *
 * @author Mila
 */

@Service
public class AppUserDetailsService implements UserDetailsService {

    private final KorisnikRepository korisnici;
    private final RadnikRepository radnici;

    public AppUserDetailsService(KorisnikRepository korisnici, RadnikRepository radnici) {
        this.korisnici = korisnici;
        this.radnici = radnici;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        // 1. Prvo pokušavamo da nađemo RADNIKA
        Optional<Radnik> r = radnici.findByUsername(username);
        if (r.isPresent()) {
            Radnik radnik = r.get();
            return new org.springframework.security.core.userdetails.User(
                    radnik.getUsername(),
                    radnik.getPassword(), // password u bazi mora biti BCrypt hash
                    List.of(new SimpleGrantedAuthority("RADNIK"))
            );
        }

        // 2. Ako nije radnik, pokušavamo da nađemo KORISNIKA (KUPCA)
        Optional<Korisnik> k = korisnici.findByUsername(username);
        if (k.isPresent()) {
            Korisnik korisnik = k.get();
            return new org.springframework.security.core.userdetails.User(
                    korisnik.getUsername(),
                    korisnik.getPassword(),
                    List.of(new SimpleGrantedAuthority("KUPAC"))
            );
        }

        // 3. Ako nije pronađen ni u jednoj tabeli, bacamo grešku
        throw new UsernameNotFoundException("User not found with username: " + username);
    }
}