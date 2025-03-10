package com.owasp.owaspbe.cryptographicfailures.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "cf_users")
public class CFUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password; // ❌ Stocăm parola hashuită cu MD5

    @Column(nullable = false)
    private String email;

    public CFUser() {}

    public CFUser(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    // Getters și setters
}
