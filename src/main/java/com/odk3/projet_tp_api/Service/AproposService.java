package com.odk3.projet_tp_api.Service;

import com.odk3.projet_tp_api.Repository.AproposRepository;
import com.odk3.projet_tp_api.Repository.EnseignantRepository;
import com.odk3.projet_tp_api.exception.DuplicateException;
import com.odk3.projet_tp_api.exception.NotFoundException;
import com.odk3.projet_tp_api.model.Apropos;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AproposService {
    @Autowired
    AproposRepository aproposRepository;

    @Autowired
    EnseignantRepository enseignantRepository;

    public Apropos creerApropos(Apropos apropos){
        if (aproposRepository.findByIdApropos(apropos.getIdApropos()) == null) {

            return aproposRepository.save(apropos);
        } else {
            throw new DuplicateException("Cet apropos existe déjà");
        }

    }

    public Apropos modifierApropos(Apropos apropos) {

        if (aproposRepository.findByIdApropos(apropos.getIdApropos()) != null){
            return aproposRepository.save(apropos);
        }
        else {
            throw  new NotFoundException("Cet apropos n'existe pas");
        }
    }

    public Apropos getParIdEnseignant(int idEnseignant){

        // Obtention de tous les budget dans la base de donnÃ©es
        Apropos apropos = aproposRepository.findByEnseignantIdEnseignant(idEnseignant);
        // Si la liste est vide, le systÃ¨me lÃ¨vera une exception
        if (apropos == null)
            throw new EntityNotFoundException("Apropos non completer !");
        // Dans le cas contraire le systÃ¨me retourne la liste
        return apropos;
    }


}
