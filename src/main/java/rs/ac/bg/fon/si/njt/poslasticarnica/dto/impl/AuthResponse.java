/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.si.njt.poslasticarnica.dto.impl;

/**
 *
 * @author Mila
 */
public class AuthResponse {
    private String username;
    private Role role;    // Ovde će pisati KUPAC ili RADNIK
    private String token;
    private Long id;

    public AuthResponse() {
    }

    public AuthResponse(String username, Role role, String token, Long id) {
        this.username = username;
        this.role = role;
        this.token = token;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    
    
}
