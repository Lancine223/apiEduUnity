package com.odk3.projet_tp_api.Service;

import com.odk3.projet_tp_api.Repository.FiliereRepository;
import com.odk3.projet_tp_api.exception.DuplicateException;
import com.odk3.projet_tp_api.exception.NoContentException;
import com.odk3.projet_tp_api.exception.NotFoundException;
import com.odk3.projet_tp_api.model.Filiere;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FiliereService {
    @Autowired
    FiliereRepository filiereRepository;

    public Filiere creerFiliere(Filiere filiere){
        if (filiereRepository.findByIdFiliere(filiere.getIdFiliere()) == null) {
            return filiereRepository.save(filiere);
        } else {
            throw new DuplicateException("Cet Filiere existe déjà");
        }

    }

    public List<Filiere> listFilieres() {
        if (!filiereRepository.findAll().isEmpty())
            return filiereRepository.findAll();
        else
            throw new NoContentException("Aucun Filiere n'a été trouver");
    }
    public Filiere modifierFiliere(Filiere filiere) {

        if (filiereRepository.findByIdFiliere(filiere.getIdFiliere()) != null){
            return filiereRepository.save(filiere);
        }
        else {
            throw  new NotFoundException("Cet Filiere n'existe pas");
        }

    }
    //++++++++++++++++++++++++
    public List<Filiere> ListFiliereParNiveau(int idNiveau){

        // Obtention de tous les budget dans la base de donnÃ©es
        List<Filiere> ListNiveaux = filiereRepository.findByNiveauIdNiveau(idNiveau);

        // Si la liste est vide, le systÃ¨me lÃ¨vera une exception
        if (ListNiveaux.isEmpty())
            throw new EntityNotFoundException("Aucun Filiere trouvé");
        // Dans le cas contraire le systÃ¨me retourne la liste
        return ListNiveaux;
    }

    public Filiere getFiliereById(int id){
        Filiere filiere = filiereRepository.findByIdFiliere(id);
        if(filiere != null)
            return filiere;
        else
            throw new NotFoundException("Cet Filiere n'existe pas");
    }
    //+++++++++
    public String supprimeFiliere(Filiere filiere) {

        if (filiereRepository.findByIdFiliere(filiere.getIdFiliere()) != null) {
            filiereRepository.delete(filiere);
            return "Succès";
        } else {
            throw  new NotFoundException("Cet Filiere n'existe pas");
        }

    }
}
