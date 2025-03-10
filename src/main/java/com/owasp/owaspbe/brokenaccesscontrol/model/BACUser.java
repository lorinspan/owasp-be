package com.owasp.owaspbe.brokenaccesscontrol.model;

import jakarta.persistence.*;

@Entity
@Table(name = "bac_users")
public class BACUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password; // Stocarea parolelor fără hashing ❌ Parola nu e protejată prin hashing
    private String role; // "user" sau "admin"

    public BACUser() {}

    public BACUser(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public Long getId() {
        return id;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
