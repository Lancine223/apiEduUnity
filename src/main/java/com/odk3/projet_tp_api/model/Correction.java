package com.odk3.projet_tp_api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Correction {
    @Id
    // Pour mettre le type en auto incremente
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    // Not Null de base de donnée
    @Column(nullable = false)
    private int idCorrection;

    // Pour monter que le text est long
    @Lob

    // Not Null de base de donnée
    @Column(nullable = false)
    private String titre;

    // Not Null de base de donnée
    @Column(nullable = false)
    private String fichier;

    @ManyToOne
    @JoinColumn(name = "idEtudiant", nullable = false)
    private Etudiant etudiant;

    @ManyToOne
    @JoinColumn(name = "idDevoir", nullable= false)
    @JsonIgnoreProperties(value = {"etudiant","correction"})
    private Devoir devoir;

    @OneToMany(mappedBy = "correction", orphanRemoval = true)
    @JsonIgnoreProperties(value = {"etudiant","correction"})
    private List<Evaluation> evaluations = new ArrayList<>();

}
