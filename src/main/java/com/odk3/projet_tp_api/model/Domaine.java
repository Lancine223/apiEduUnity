package com.odk3.projet_tp_api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Domaine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int idDomaine;

    @NotNull(message = "champs nom_domaine est vide")
    @Column(nullable = false)
    private String nom;

    @NotNull(message = "veuillez choisir une photo")
    @Column(nullable = false)
    private String description;

    @OneToMany(mappedBy = "domaine", orphanRemoval = true)
    @JsonIgnore
    private List<Niveau> niveauList = new ArrayList<>();
    
}
