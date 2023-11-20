package com.odk3.projet_tp_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EtudiantPodjo {

    private String nom;

    private String prenom;

    private int classe;

    private int filiere;

    private int niveau;

    private String telephone;

    private int age;

    private String motDePasse;

    private boolean estAbonner;
}
