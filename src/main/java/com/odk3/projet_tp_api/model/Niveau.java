package com.odk3.projet_tp_api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @OneToMany(mappedBy = "niveau", orphanRemoval = true)
    @JsonIgnore
    private List<Filiere> filiere = new ArrayList<>();

    @OneToMany(mappedBy = "niveau", orphanRemoval = true)
    @JsonIgnore
    private List<Enseignant> enseignant = new ArrayList<>();

    @OneToMany(mappedBy = "niveau", orphanRemoval = true)
    @JsonIgnore
    private List<Etudiant> etudiant = new ArrayList<>();
}
