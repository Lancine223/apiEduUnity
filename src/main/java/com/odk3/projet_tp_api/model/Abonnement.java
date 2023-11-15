package com.odk3.projet_tp_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class Abonnement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(nullable = false)
    private int idAbonnement;

    @NotNull(message = "le champ telephone est vide")
    @Column(nullable = false)
    private int telephone;

    @NotNull(message = "le champ montant est vide")
    @Column(nullable = false)
    private int montant;

    @NotNull(message = "la date est vide")
    @Column(nullable = false)
    private Date dateAbonnement;

    @ManyToOne
    @JoinColumn(name = "idEnseignant", nullable = false)
    private Enseignant enseignant;
    
    @ManyToOne
    @JoinColumn(name = "idEtudiant", nullable = false)
    private Etudiant etudiant;
}
