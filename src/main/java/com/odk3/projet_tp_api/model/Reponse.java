package com.odk3.projet_tp_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class Reponse {
    @Id

    // Pour mettre le type en auto incremente
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    // Not Null de base de donnée
    @Column(nullable = false)
    private int idReponse;

    @Lob
    @Size(min = 2, message = "contenue court")

    // Not Null de base de donnée
    @Column(nullable = false)
    private  String contenue;

    // Not Null de base de donnée
    @Column(nullable = false)
    private boolean correct;


    @ManyToOne
    //Joindre le colonne
    @JoinColumn(name = "idQuestion", nullable = false)
    //@JsonIgnoreProperties(value = {"utilisateur","quiz","reponses"})
    private Question question;

    @ManyToOne
    // Joindre colonne
    @JoinColumn(name = "idEnseignant", nullable = false)
    private Enseignant enseignant;

}
