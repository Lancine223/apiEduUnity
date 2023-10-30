package com.odk3.projet_tp_api.Service;

import com.odk3.projet_tp_api.Repository.AdministrateurRepository;
import com.odk3.projet_tp_api.Repository.EnseignantRepository;
import com.odk3.projet_tp_api.exception.DuplicateException;
import com.odk3.projet_tp_api.exception.NoContentException;
import com.odk3.projet_tp_api.exception.NotFoundException;
import com.odk3.projet_tp_api.model.Administrateur;
import com.odk3.projet_tp_api.model.Enseignant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnseignantService {
    // Pour acceder a la table utilisateur dans la base de donnée
    @Autowired // Injection de dependance
            EnseignantRepository enseignantRepository; // Un variable de type UtilisateurRepository

    // Portee , type de retour , nom de la fonction
    public Enseignant creerEnseignant(Enseignant enseignant){
        if (enseignantRepository.findByEmail(enseignant.getEmail()) == null) {
            return enseignantRepository.save(enseignant);
        } else {
            throw new DuplicateException("Cet email existe déjà");
        }

    }

    public List<Enseignant> listEnseignants() {
        if (!enseignantRepository.findAll().isEmpty())
            return enseignantRepository.findAll();
        else
            throw new NoContentException("Aucun enseignant n'a été trouver");
    }

    public Enseignant modifierEnseignant(Enseignant enseignant) {

        if (enseignantRepository.findByIdEnseignant(enseignant.getIdEnseignant()) != null){
            return enseignantRepository.save(enseignant);
        }
        else {
            throw  new NotFoundException("Cet enseignant n'existe pas");
        }

    }


    public String supprimeEnseignant(Enseignant enseignant) {

        if (enseignantRepository.findByIdEnseignant(enseignant.getIdEnseignant()) != null) {
            enseignantRepository.delete(enseignant);
            return "Succès";
        } else {
            throw  new NotFoundException("Cet enseignant n'existe pas");
        }

    }

    public Enseignant connectionEnseignant(String email, String mon_de_passe) {
        if (enseignantRepository.findByEmailAndMotDePasse(email, mon_de_passe) != null) {
            return (Enseignant) enseignantRepository.findByEmailAndMotDePasse(email, mon_de_passe);
        }else {
            throw  new NotFoundException("Cet enseignant n'existe pas");
        }

    }
}
