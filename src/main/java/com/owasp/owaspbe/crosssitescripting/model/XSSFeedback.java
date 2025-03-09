package com.owasp.owaspbe.crosssitescripting.model;

import jakarta.persistence.*;

@Entity
@Table(name = "feedback")
public class XSSFeedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String message;
}
