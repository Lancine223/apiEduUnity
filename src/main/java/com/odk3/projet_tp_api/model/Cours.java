package com.odk3.projet_tp_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
public class Cours {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int idCours;

    @NotNull(message = "le champs titre est vide")
    @Column(nullable = false)
    private String titre;

    @NotNull(message = "le champs contenu est vide")
    @Column(nullable = false)
    private String document;

    @ManyToOne
    @JoinColumn(name = "idEnseignant", nullable = false)
    private Enseignant enseignant;


}
