package com.odk3.projet_tp_api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Evaluation {

    @Id

    // Pour mettre le type en auto incremente
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    // Not Null de base de donnée
    @Column(nullable = false)
    private int idEvaluation;

    @Lob
    @Size(min = 2, message = "contenue court")

    // Not Null de base de donnée
    @Column(nullable = false)
    private  String titre;

    // Not Null de base de donnée
    @Column(nullable = false)
    private int note;

    // Not Null de base de donnée
    @Column(nullable = false)
    private String fichier;


    @ManyToOne
    //Joindre le colonne
    @JoinColumn(name = "idCorrection", nullable = false)
    @JsonIgnoreProperties(value = {"enseignant","devoir","evaluation"})
    private Correction correction;

    @ManyToOne
    // Joindre colonne
    @JoinColumn(name = "idEnseignant", nullable = false)
    private Enseignant enseignant;
}
