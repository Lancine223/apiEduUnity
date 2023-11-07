package com.odk3.projet_tp_api.Service;

import com.odk3.projet_tp_api.Repository.NiveauRepository;
import com.odk3.projet_tp_api.exception.DuplicateException;
import com.odk3.projet_tp_api.exception.NoContentException;
import com.odk3.projet_tp_api.exception.NotFoundException;
import com.odk3.projet_tp_api.model.Abonnement;
import com.odk3.projet_tp_api.model.Niveau;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NiveauService {
    @Autowired
    NiveauRepository niveauRepository;


    public Niveau creerNiveau(Niveau niveau){
        if (niveauRepository.findByIdNiveau(niveau.getIdNiveau()) == null) {
            return niveauRepository.save(niveau);
        } else {
            throw new DuplicateException("Cet niveau existe déjà");
        }

    }

    public List<Niveau> listNiveaus() {
        if (!niveauRepository.findAll().isEmpty())
            return niveauRepository.findAll();
        else
            throw new NoContentException("Aucun niveau n'a été trouver");
    }
    public Niveau modifierNiveau(Niveau niveau) {

        if (niveauRepository.findByIdNiveau(niveau.getIdNiveau()) != null){
            return niveauRepository.save(niveau);
        }
        else {
            throw  new NotFoundException("Cet niveau n'existe pas");
        }

    }
    //++++++++++++++++++++++++

    //+++++++++
    public String supprimeNiveau(Niveau niveau) {

        if (niveauRepository.findByIdNiveau(niveau.getIdNiveau()) != null) {
            niveauRepository.delete(niveau);
            return "Succès";
        } else {
            throw  new NotFoundException("Cet niveau n'existe pas");
        }

    }
}
