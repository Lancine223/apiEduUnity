package com.odk3.projet_tp_api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Classe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int idClasse;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private int montant;

    @ManyToOne
    @JoinColumn(name = "idFiliere", nullable = false)
    private Filiere filiere;

    @OneToMany(mappedBy = "classe", orphanRemoval = true)
    @JsonIgnore
    private List<Enseignant> enseignant = new ArrayList<>();

    @OneToMany(mappedBy = "classe", orphanRemoval = true)
    @JsonIgnore
    private List<Etudiant> etudiant = new ArrayList<>();
}
