package com.odk3.projet_tp_api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
public class Participer {

    @Id
    // Pour mettre le type en auto incremente
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    // Not Nul dans la Base de donnée
    @NotNull(message = "Champs laisser vide")
    @Column(nullable = false)
    private int idParticiper;

    // Not Null dans la Spring
    @NotNull(message = "champs null")

    // Not Null dans la Base de donnée
    @NotNull(message = "champs null")
    @Column(nullable = false)
    private int score;

    @NotNull(message = "champs null")
    @Column(nullable = false)
    private int level;

    @NotNull(message = "champs null")
    @Column(nullable = false)
    private boolean terminer;

    @ManyToOne
    //Joindre le colonne
    @JoinColumn(name = "idEtudiant",nullable = false)
    private Etudiant etudiant;

    @ManyToOne
    @JoinColumn(name = "idQuiz", nullable = false)
    @JsonIgnore
    private Quiz quiz;
}

