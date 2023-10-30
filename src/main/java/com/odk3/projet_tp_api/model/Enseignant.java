package com.odk3.projet_tp_api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Entity
@Data
public class Enseignant {
    @Id
    // Pour mettre le type en auto incremente
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    // Not Null de base de donnée
    @Column(nullable = false)
    private int idEnseignant;

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

    //=========================== POUR PRENOM ===========================

    // Not Null de Spring
    // message : message afficher si le champs de notre prenom est vide
    @NotNull(message = "Champs vide")

    // lenght de notre prenom
    // message : message afficher si le champs de notre prenom est vide
    @Size(min = 2,message = "veuillez saisir un noms correcte")

    // Not Null de base donnée
    @Column(nullable = false)
    private String prenom;


    //=========================== POUR Diplome ===========================

    // Not Null de Spring
    // message : message afficher si le champs de notre prenom est vide
    @NotNull(message = "Champs diplôme est vide")

    // lenght de notre prenom
    // message : message afficher si le champs de notre prenom est vide
    @Size(min = 2,message = "veuillez saisir un nom correcte")

    // Not Null de base donnée
    @Column(nullable = false)
    private String diplome;

    //========================== POUR EMAIL ============================

    // Not Null de Spring
    // message : message afficher
    // message : message afficher si le champs de notre email est vide
    @NotNull(message = "Champs vide")

    // L'Anotation d'Email
    // message : message afficher si le champs de notre email est vide
    @Email(message = "email n'est pas valide")

    // Not Null de base de donnée
    @Column(nullable = false)
    private String email;

    //======================= POUR MOT DE PASSE ===============================

    // Not Null de Spring
    @NotNull(message = "Champs vide")
    // size : lenght de notre Mot de passe
    // message : message afficher si le champs de notre Mot de passe est vide
    @Size(min = 6, message = "Saisissez un Mot de pass correct")
    // Not Null de base de donnée
    @Column(nullable = false)
    private String motDePasse;

    // ============== NiveauRepository =====================
    @ManyToOne
    @JoinColumn(name = "idNiveaux",nullable = false)
    private Niveau niveau;
    // ============== AproposRepository =====================
    @OneToOne
    @JoinColumn(name = "idApropos")
    private Apropos apropos;
    // ============== AdministrateurRepository =====================

    @ManyToOne
    @JoinColumn(name = "idAdministrateur",nullable = false)
    private Administrateur administrateur;

    // ============== AbonnementRepository =====================

    @OneToMany(mappedBy = "enseignant", orphanRemoval = true)
    @JsonIgnore
    private List<Abonnement> abonnement = new ArrayList<>();

    // ========================== NotationRepository ==============
    @OneToMany(mappedBy = "enseignant", orphanRemoval = true)
    @JsonIgnore
    private List<Notation> notationList = new ArrayList<>();


    // ============== Quiz =====================

    @OneToMany(mappedBy = "enseignant", orphanRemoval = true)
    @JsonIgnore
    private List<Quiz> quizs = new ArrayList<>();

    // ============== CoursRepository =====================

    @OneToMany(mappedBy = "enseignant", orphanRemoval = true)
    @JsonIgnore
    private List<Cours> cours = new ArrayList<>();

    // ============== devoir =====================

    @OneToMany(mappedBy = "enseignant", orphanRemoval = true)
    @JsonIgnore
    private List<Devoir> devoirs = new ArrayList<>();

    // ============== forum =====================

    @OneToMany(mappedBy = "enseignant", orphanRemoval = true)
    @JsonIgnore
    private List<Forum> forums = new ArrayList<>();

    // ============== videos =====================

    @OneToMany(mappedBy = "enseignant", orphanRemoval = true)
    @JsonIgnore
    private List<Videos> videos = new ArrayList<>();

    // ============== NotationRepository =====================

    @OneToMany(mappedBy = "enseignant", orphanRemoval = true)
    @JsonIgnore
    private List<Notation> notations = new ArrayList<>();
    // ============== Participant =====================
    @OneToMany(mappedBy = "enseignant", orphanRemoval = true)
    @JsonIgnore
    private List<Participer> participerList = new ArrayList<>();
    
}