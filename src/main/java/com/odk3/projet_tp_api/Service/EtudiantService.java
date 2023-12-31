package com.odk3.projet_tp_api.Service;

import com.odk3.projet_tp_api.Repository.AbonnementRepository;
import com.odk3.projet_tp_api.Repository.EtudiantRepository;
import com.odk3.projet_tp_api.Repository.StatusEtudiantRepository;
import com.odk3.projet_tp_api.exception.DuplicateException;
import com.odk3.projet_tp_api.exception.NoContentException;
import com.odk3.projet_tp_api.exception.NotFoundException;
import com.odk3.projet_tp_api.model.Abonnement;
import com.odk3.projet_tp_api.model.Etudiant;
import com.odk3.projet_tp_api.model.StatusEtudiant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EtudiantService {
    @Autowired
    EtudiantRepository etudiantRepository;
    @Autowired
    AbonnementRepository abonnementRepository;

    @Autowired
    StatusEtudiantRepository statusEtudiantRepository;
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

    public Etudiant modifierPodjo(Etudiant etudiant) {
        Etudiant EtudiantExitant = etudiantRepository.findByIdEtudiant(etudiant.getIdEtudiant());
        if (EtudiantExitant != null) {
            EtudiantExitant.setNom(etudiant.getNom());
            EtudiantExitant.setPrenom(etudiant.getPrenom());
            EtudiantExitant.setAge(etudiant.getAge());
            EtudiantExitant.setMotDePasse(etudiant.getMotDePasse());
            EtudiantExitant.setTelephone(etudiant.getTelephone());
            return etudiantRepository.save(EtudiantExitant);
        } else {
            throw new NotFoundException("Cet etudiant n'existe pas");
        }
    }

    public String supprimeEtudiant(Etudiant etudiant) {
        StatusEtudiant statusEtudiant = statusEtudiantRepository.findByEtudiantIdEtudiant(etudiant.getIdEtudiant());
        if (etudiantRepository.findByIdEtudiant(etudiant.getIdEtudiant()) != null) {
            if(statusEtudiant != null){
                statusEtudiantRepository.delete(statusEtudiant);
            }
            etudiantRepository.delete(etudiant);
            return "Succès";
        } else {
            throw  new NotFoundException("Cet etudiant n'existe pas");
        }

    }

    public Etudiant connectionEtudiant(String telephone, String motDePasse) {

        Etudiant etudiant = etudiantRepository.findByTelephoneAndMotDePasse(telephone, motDePasse);
        if ( etudiant != null) {
            return etudiant;
        }else {
            throw  new NotFoundException("Cet etudiant n'existe pas");
        }
    }
}
