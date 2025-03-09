package com.owasp.owaspbe.brokenauthentication.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ba_users") // ❌ Tabel separat, fără hashing
public class BrokenAuthenticationUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password; // ❌ Parolele sunt în clar

    // Getters și Setters
}
