package com.odk3.projet_tp_api.Repository;

import com.odk3.projet_tp_api.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur,Integer> {

    public Utilisateur findByEmail(String email);

    public Utilisateur findByEmailAndMotDePasse(String email, String mot_de_passe);

    public Utilisateur findByIdUtilisateur(int id);
}
