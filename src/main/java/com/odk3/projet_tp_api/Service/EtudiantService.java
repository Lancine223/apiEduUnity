package com.odk3.projet_tp_api.Service;

import com.odk3.projet_tp_api.Repository.AbonnementRepository;
import com.odk3.projet_tp_api.Repository.EtudiantRepository;
import com.odk3.projet_tp_api.exception.DuplicateException;
import com.odk3.projet_tp_api.exception.NoContentException;
import com.odk3.projet_tp_api.exception.NotFoundException;
import com.odk3.projet_tp_api.model.Abonnement;
import com.odk3.projet_tp_api.model.Etudiant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EtudiantService {
    @Autowired
    EtudiantRepository etudiantRepository;
    AbonnementRepository abonnementRepository;
    // Portee , type de retour , nom de la fonction
    public Etudiant creerEtudiant(Etudiant etudiant){
        if (etudiantRepository.findByTelephone(etudiant.getTelephone()) == null) {
            return etudiantRepository.save(etudiant);
        } else {
            throw new DuplicateException("Cet email existe déjà");
        }

    }

    public boolean estAbonne(int idEtudiant) {
        List<Abonnement> abonnements = abonnementRepository.findByEtudiantIdEtudiant(idEtudiant);
        return !abonnements.isEmpty();
    }

    public List<Etudiant> listEtudiants() {
        if (!etudiantRepository.findAll().isEmpty())
            return etudiantRepository.findAll();
        else
            throw new NoContentException("Aucun etudiant n'a été trouver");
    }

    public Etudiant modifierEtudiant(Etudiant etudiant) {

        if (etudiantRepository.findByIdEtudiant(etudiant.getIdEtudiant()) != null){
            return etudiantRepository.save(etudiant);
        }
        else {
            throw  new NotFoundException("Cet etudiant n'existe pas");
        }

    }


    public String supprimeEtudiant(Etudiant etudiant) {

        if (etudiantRepository.findByIdEtudiant(etudiant.getIdEtudiant()) != null) {
            etudiantRepository.delete(etudiant);
            return "Succès";
        } else {
            throw  new NotFoundException("Cet etudiant n'existe pas");
        }

    }

    public Etudiant connectionEtudiant(String telephone, String mon_de_passe) {
        if (etudiantRepository.findByTelephoneAndMotDePasse(telephone, mon_de_passe) != null) {
            return (Etudiant) etudiantRepository.findByTelephoneAndMotDePasse(telephone, mon_de_passe);
        }else {
            throw  new NotFoundException("Cet etudiant n'existe pas");
        }
    }
}
