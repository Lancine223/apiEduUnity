package com.odk3.projet_tp_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class Videos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int idVideos;

    @NotNull(message = "le champs titre est vide")
    @Column(nullable = false)
    private String titre;

    @NotNull(message = "selectionner une video")
    @Column(nullable = false)
    private String video;

    @ManyToOne
    @JoinColumn(name = "idEnseignant", nullable = false)
    private Enseignant enseignant;
    
}
