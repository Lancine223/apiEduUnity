package com.odk3.projet_tp_api.Service;

import com.odk3.projet_tp_api.Repository.UtilisateurRepository;
import com.odk3.projet_tp_api.exception.DuplicateException;
import com.odk3.projet_tp_api.exception.NoContentException;
import com.odk3.projet_tp_api.exception.NotFoundException;
import com.odk3.projet_tp_api.model.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//@Service
public class UtilisateurService {
/*
    // Pour acceder a la table utilisateur dans la base de donnée
    @Autowired // Injection de dependance
    UtilisateurRepository utilisateurRepository; // Un variable de type UtilisateurRepository

    // Portee , type de retour , nom de la fonction
    public Utilisateur creerUtilisateur(Utilisateur utilisateur){
        if (utilisateurRepository.findByEmail(utilisateur.getEmail()) == null) {
            return utilisateurRepository.save(utilisateur);
        } else {
            throw new DuplicateException("Cet email existe déjà");
        }

    }

    public List<Utilisateur> listUtilisateurs() {
        if (!utilisateurRepository.findAll().isEmpty())
            return utilisateurRepository.findAll();
        else
            throw new NoContentException("Aucun utilisateur n'a été trouver");
    }
    public Utilisateur modifierUtilisateur(Utilisateur utilisateur) {

        if (utilisateurRepository.findByIdUtilisateur(utilisateur.getIdUtilisateur()) != null){
            return utilisateurRepository.save(utilisateur);
        }
        else {
            throw  new NotFoundException("Cet utilisateur n'existe pas");
        }

    }
    //++++++++++++++++++++++++
    public String supprimeUtilisateur(Utilisateur utilisateur) {

        if (utilisateurRepository.findByIdUtilisateur(utilisateur.getIdUtilisateur()) != null) {
            utilisateurRepository.delete(utilisateur);
            return "Succès";
        } else {
            throw  new NotFoundException("Cet utilisateur n'existe pas");
        }

    }

    public Utilisateur connectionUtilisateur(String email, String mon_de_passe) {
        if (utilisateurRepository.findByEmailAndMotDePasse(email, mon_de_passe) != null) {
            return utilisateurRepository.findByEmailAndMotDePasse(email, mon_de_passe);
        }else {
            throw  new NotFoundException("Cet utilisateur n'existe pas");
        }

    }
*/

}
