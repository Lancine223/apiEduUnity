package com.odk3.projet_tp_api.Service;

import com.odk3.projet_tp_api.Repository.PreinscriptionEnseignantRepository;
import com.odk3.projet_tp_api.exception.DuplicateException;
import com.odk3.projet_tp_api.exception.NoContentException;
import com.odk3.projet_tp_api.exception.NotFoundException;
import com.odk3.projet_tp_api.model.PreinscriptionEnseignant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PreinscriptionEnseignantService {
    @Autowired
    PreinscriptionEnseignantRepository preinscriptionEnseignantRepository;

    // Portee , type de retour , nom de la fonction
    public PreinscriptionEnseignant creerPreinscriptionEnseignant(PreinscriptionEnseignant preinscriptionEnseignant){
        if (preinscriptionEnseignantRepository.findByEmail(preinscriptionEnseignant.getEmail()) == null) {
            return preinscriptionEnseignantRepository.save(preinscriptionEnseignant);
        } else {
            throw new DuplicateException("Cet email existe déjà");
        }

    }

    public List<PreinscriptionEnseignant> listPreinscriptionEnseignants() {
        if (!preinscriptionEnseignantRepository.findAll().isEmpty())
            return preinscriptionEnseignantRepository.findAll();
        else
            throw new NoContentException("Aucun enseignant n'a été trouver");
    }
    //++++++++++++++++++++++++
    public String supprimePreinscriptionEnseignant(PreinscriptionEnseignant preinscriptionEnseignant) {

        if (preinscriptionEnseignantRepository.findByIdPre(preinscriptionEnseignant.getIdPreinscrit()) != null) {
            preinscriptionEnseignantRepository.delete(preinscriptionEnseignant);
            return "Succès";
        } else {
            throw  new NotFoundException("Cet enseingant n'existe pas");
        }

    }

}
