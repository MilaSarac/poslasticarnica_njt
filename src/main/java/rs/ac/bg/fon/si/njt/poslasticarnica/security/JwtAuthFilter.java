/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.si.njt.poslasticarnica.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 *
 * @author Mila
 */

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwt;
    private final AppUserDetailsService uds;

    public JwtAuthFilter(JwtService jwt, AppUserDetailsService uds) {
        this.jwt = jwt;
        this.uds = uds;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain ch)
            throws ServletException, IOException {

        // 1. Izvlačimo "Authorization" header
        String auth = req.getHeader("Authorization");

        // 2. Proveravamo da li header postoji i da li počinje sa "Bearer "
        if (auth != null && auth.startsWith("Bearer ")) {
            String token = auth.substring(7); // Uzimamo sve posle "Bearer "
            String username = null;

            try {
                // Pokušavamo da izvučemo korisničko ime iz tokena
                username = jwt.extractUsername(token);
            } catch (Exception ignored) {
                // Ako je token neispravan, ignorišemo grešku i username ostaje null
            }

            // 3. Ako imamo username, a korisnik još uvek nije autentifikovan u sistemu
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                
                // Učitavamo korisnika iz baze (preko uds - AppUserDetailsService)
                UserDetails ud = uds.loadUserByUsername(username);

                // 4. Proveravamo da li je token i dalje validan za tog korisnika
                if (jwt.isValid(token, ud)) {
                    // Ako je sve u redu, pravimo autentifikacioni token za Spring Security
                    UsernamePasswordAuthenticationToken at =
                            new UsernamePasswordAuthenticationToken(ud, null, ud.getAuthorities());
                    
                    // Dodajemo detalje zahteva (IP adresa, session itd.)
                    at.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
                    
                    // POSTAVLJAMO KORISNIKA KAO ULOGOVANOG
                    SecurityContextHolder.getContext().setAuthentication(at);
                }
            }
        }

        // Nastavljamo na sledeći filter u lancu
        ch.doFilter(req, res);
    }
}
