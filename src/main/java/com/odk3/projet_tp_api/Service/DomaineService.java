package com.odk3.projet_tp_api.Service;

import com.odk3.projet_tp_api.Repository.DomaineRepository;
import com.odk3.projet_tp_api.exception.DuplicateException;
import com.odk3.projet_tp_api.exception.NoContentException;
import com.odk3.projet_tp_api.exception.NotFoundException;
import com.odk3.projet_tp_api.model.Domaine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DomaineService {
    @Autowired
    DomaineRepository domaineRepository;

    public Domaine creerDomaine(Domaine domaine){
        if (domaineRepository.findByIdDomaine(domaine.getIdDomaine()) == null) {
            return domaineRepository.save(domaine);
        } else {
            throw new DuplicateException("Cet domaine existe déjà");
        }

    }

    public List<Domaine> listDomaines() {
        if (!domaineRepository.findAll().isEmpty())
            return domaineRepository.findAll();
        else
            throw new NoContentException("Aucun domaine n'a été trouver");
    }
    public Domaine modifierDomaine(Domaine domaine) {

        if (domaineRepository.findByIdDomaine(domaine.getIdDomaine()) != null){
            return domaineRepository.save(domaine);
        }
        else {
            throw  new NotFoundException("Cet domaine n'existe pas");
        }

    }
    //++++++++++++++++++++++++
    public String supprimeDomaine(Domaine domaine) {

        if (domaineRepository.findByIdDomaine(domaine.getIdDomaine()) != null) {
            domaineRepository.delete(domaine);
            return "Succès";
        } else {
            throw  new NotFoundException("Cet domaine n'existe pas");
        }

    }
}
