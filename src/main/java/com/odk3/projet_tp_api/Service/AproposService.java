package com.odk3.projet_tp_api.Service;

import com.odk3.projet_tp_api.Repository.AproposRepository;
import com.odk3.projet_tp_api.exception.DuplicateException;
import com.odk3.projet_tp_api.exception.NotFoundException;
import com.odk3.projet_tp_api.model.Apropos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AproposService {
    @Autowired
    AproposRepository aproposRepository;

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


}
