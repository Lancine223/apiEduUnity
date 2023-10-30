package com.odk3.projet_tp_api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
public class Discussion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int idDiscussion;

    @NotNull(message = "veuillez choisir le message")
    private String message;

    @ManyToOne
    @JoinColumn(name = "idForum", nullable = false)
    @JsonIgnoreProperties(value = {"etudiant","enseignant","discussion"})
    private Forum forum;

    @ManyToOne
    @JoinColumn(name = "idEtudiant")
    private Etudiant etudiant;

    @ManyToOne
    @JoinColumn(name = "idEnseignant")
    private Enseignant enseignant;
    
}
