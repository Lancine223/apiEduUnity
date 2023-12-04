package com.odk3.projet_tp_api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Entity
@Data
public class Etudiant {
    @Id
    // Pour mettre le type en auto incremente
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    // Not Null de base de donnée
    @Column(nullable = false)
    private int idEtudiant;
    //=========================== POUR NOM ===========================

    // Not Null de Spring
    // message : message afficher si le champs de notre Nom est vide
    @NotNull(message = "Champs vide")

    // lenght de notre nom
    // message : message afficher si le champs de notre Nom est vide
    @Size(min = 2, message = "nom court")

    // Not Null de base de donnée
    @Column(nullable = false)
    private String nom;


    //=========================== POUR le status ===========================
    @OneToOne
    @JoinColumn(name = "idStatusEtudiant")
    private StatusEtudiant statusEtudiant;

    //=========================== POUR PRENOM ===========================

    // Not Null de Spring
    // message : message afficher si le champs de notre prenom est vide
    @NotNull(message = "Champs vide")

    // lenght de notre prenom
    // message : message afficher si le champs de notre prenom est vide
    @Size(min = 2,message = "veuillez saisir un nom correcte")

    // Not Null de base donnée
    @Column(nullable = false)
    private String prenom;

    // ============== ClasseRepository =====================
    @ManyToOne
    @JoinColumn(name = "idClasse")
    private Classe classe;

    @ManyToOne
    @JoinColumn(name = "idFiliere", nullable = false)
    private Filiere filiere;

    @ManyToOne
    @JoinColumn(name = "idNiveau", nullable = false)
    private Niveau niveau;

    // pour le telephone

    @NotNull(message = "Champs vide")

    // L'Anotation d'Email


    // Not Null de base de donnée
    @Column(nullable = false)
    private String telephone;

    // pour l' age

    @NotNull(message = "Champs vide")
    // Not Null de base de donnée
    @Column(nullable = false)
    private int age;
    //======================= POUR MOT DE PASSE ===============================

    // Not Null de Spring
    @NotNull(message = "Champs vide")

    // size : lenght de notre Mot de passe
    // message : message afficher si le champs de notre Mot de passe est vide
    @Size(min = 6, message = "Saisissez un Mot de pass correct")

    // Not Null de base de donnée
    @Column(nullable = false)
    private String motDePasse;

    @Column(name = "est_abonner", columnDefinition = "boolean default false",nullable = false)
    private boolean estAbonner;

    // Les autres getters et setters

    public boolean isEstAbonner() {
        return estAbonner;
    }

    public void setEstAbonner(boolean estAbonner) {
        this.estAbonner = estAbonner;
    }

    @OneToMany(mappedBy = "etudiant", orphanRemoval = true)
    @JsonIgnore
    private List<Abonnement> abonnementList = new ArrayList<>();

    @OneToMany(mappedBy = "etudiant", orphanRemoval = true)
    @JsonIgnore
    private List<Correction> correctionList = new ArrayList<>();

    @OneToMany(mappedBy = "etudiant", orphanRemoval = true)
    @JsonIgnore
    private List<Discussion> discussions = new ArrayList<>();

    @OneToMany(mappedBy = "etudiant", orphanRemoval = true)
    @JsonIgnore
    private List<Notation> notationList = new ArrayList<>();

    @OneToMany(mappedBy = "etudiant", orphanRemoval = true)
    @JsonIgnore
    private List<TodoList> todoListList = new ArrayList<>();


    @OneToMany(mappedBy = "etudiant", orphanRemoval = true)
    @JsonIgnore
    private List<Participer> participerList = new ArrayList<>();
}
