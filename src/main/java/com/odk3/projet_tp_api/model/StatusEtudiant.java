package com.odk3.projet_tp_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
public class StatusEtudiant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int idStatusEtudiant;

    @NotNull(message = "le nom examen est vide")
    @Column(nullable = false)
    private String examen;

    @NotNull(message = "la photo du bulletin n'est pas selectionner")
    @Column(nullable = false)
    private String bulletin;

    @OneToOne
    @JoinColumn(name = "idEtudiant", nullable = false)
    private Etudiant etudiant;
}
