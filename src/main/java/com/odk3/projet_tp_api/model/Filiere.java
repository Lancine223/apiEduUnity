package com.odk3.projet_tp_api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Filiere {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int idFiliere;

    @Column(nullable = false)
    private String nom;

    @ManyToOne
    @JoinColumn(name = "idNiveau", nullable = false)
    private Niveau niveau;

    @OneToMany(mappedBy = "filiere", orphanRemoval = true)
    @JsonIgnore
    private List<Classe> classe = new ArrayList<>();
}
