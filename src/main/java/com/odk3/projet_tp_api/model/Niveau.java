package com.odk3.projet_tp_api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Niveau {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int idNiveau;
    @NotNull(message = "le nom du est vide")
    @Column(nullable = false)
    private String nom;

    @ManyToOne
    @JoinColumn(name = "idDomaine", nullable = false)
    @JsonIgnoreProperties(value = {"enseignant","niveaux"})
    private Domaine domaine;

    @OneToMany(mappedBy = "niveau", orphanRemoval = true)
    @JsonIgnore
    private List<Enseignant> enseignantList = new ArrayList<>();

    @OneToMany(mappedBy = "niveau",orphanRemoval = true)
    @JsonIgnore
    private List<Etudiant> etudiantList = new ArrayList<>();

    
}
