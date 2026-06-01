/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.si.njt.poslasticarnica.dto.impl;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import rs.ac.bg.fon.si.njt.poslasticarnica.dto.Dto;

/**
 *
 * @author Mila
 */
public class RadnikDto implements Dto{
    
    private Long idRadnik;
    @NotBlank(message = "Name iz required.")
    private String ime;
    @NotBlank(message = "Lastname iz required.")
    private String prezime;
    @NotBlank(message = "Username je obavezan.")
    @Size(max = 20, message = "Username moze imati najvise 20 karaktera.")
    private String username;
    @NotBlank(message = "Password je obavezan.")
    private String password;

    public RadnikDto() {
    }

    public RadnikDto(Long idRadnik, String ime, String prezime, String username, String password) {
        this.idRadnik = idRadnik;
        this.ime = ime;
        this.prezime = prezime;
        this.username = username;
        this.password = password;
    }

    public Long getIdRadnik() {
        return idRadnik;
    }

    public void setIdRadnik(Long idRadnik) {
        this.idRadnik = idRadnik;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
}
