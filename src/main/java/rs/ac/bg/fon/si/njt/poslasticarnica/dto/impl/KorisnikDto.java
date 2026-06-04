/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.si.njt.poslasticarnica.dto.impl;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import rs.ac.bg.fon.si.njt.poslasticarnica.dto.Dto;

/**
 *
 * @author Mila
 */

//polja koja nisu osetljiva, nema passworda
public class KorisnikDto implements Dto{
    
    private Long idKorisnik;
    @NotEmpty(message = "Name iz required.")
    @Size(min=2, max=100, message = "Name must be between longer 2 and 100 characters.")
    private String ime;
    @NotEmpty(message = "Lastname iz required.")
    @Size(min=2, max=100, message = "Lastname must be between longer 2 and 100 characters.")
    private String prezime;
    @NotBlank(message = "Broj telefona je obavezan.")
    private String brojTelefona;
    @NotBlank(message = "Email je obavezan.")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.com$", message = "Email mora sadržati @ i završavati se sa .com")
    private String email;
    
    @NotBlank(message = "Username je obavezan.")
    private String username;
    
    // WRITE_ONLY znači da lozinka može doći sa frontenda u Javu,
    // ali Java je nikada neće vratiti u JSON-u nazad ka frontendu.
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    
    private Long mestoId;

    public KorisnikDto() {
    }

    public KorisnikDto(Long idKorisnik, String ime, String prezime, String brojTelefona, String email, String username, Long mestoId) {
        this.idKorisnik = idKorisnik;
        this.ime = ime;
        this.prezime = prezime;
        this.brojTelefona = brojTelefona;
        this.email = email;
        this.username = username;
        this.mestoId = mestoId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getIdKorisnik() {
        return idKorisnik;
    }

    public void setIdKorisnik(Long idKorisnik) {
        this.idKorisnik = idKorisnik;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getBrojTelefona() {
        return brojTelefona;
    }

    public void setBrojTelefona(String brojTelefona) {
        this.brojTelefona = brojTelefona;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getMestoId() {
        return mestoId;
    }

    public void setMestoId(Long mestoId) {
        this.mestoId = mestoId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
}
