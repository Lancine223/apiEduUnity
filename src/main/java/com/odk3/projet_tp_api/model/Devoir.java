package com.odk3.projet_tp_api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
@Entity
public class Devoir {

    @Id

    // Pour mettre le type en auto incremente
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    // note null de base de donnée
    @Column(nullable = false)
    private int idDevoir ;

    @NotNull(message = "champs vide")
    @Size(min = 2, message = "titre court")

    // Not Null de base de donnée
    @Column(nullable = false)
    private String titre;

    @NotNull(message = "champs vide")
    @Size(min = 2, message = "titre court")

    // Not Null de base de donnée
    @Column(nullable = false)
    private String fichier;

    @ManyToOne
    @JoinColumn(name = "idEnseignant",nullable = false)
    private Enseignant enseignant;

    @OneToMany(mappedBy = "devoir", orphanRemoval = true) // orphan Removal permet de suppimer tout les id correspond
    @JsonIgnoreProperties(value = {"devoir","enseignant","evaluation"}) // il permet de ignorer tout les question ici
    private List<Correction> corrections = new ArrayList<>();
}
