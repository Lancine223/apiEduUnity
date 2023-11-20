package com.odk3.projet_tp_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnseignantPodjo {
    private String nom;

    private String prenom;

    private String etablissement;

    private String telephone;

    private String diplome;

    private String email;

    private String motDePasse;

    private int classe;

    private int filiere;

    private int niveau;
    // ============== AproposRepository =====================

}
