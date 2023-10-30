package com.odk3.projet_tp_api.Service;

import com.odk3.projet_tp_api.Repository.ReponseRepository;
import com.odk3.projet_tp_api.exception.DuplicateException;
import com.odk3.projet_tp_api.exception.NoContentException;
import com.odk3.projet_tp_api.exception.NotFoundException;
import com.odk3.projet_tp_api.model.Reponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReponseService {

    // Pour acceder a la table utilisateur dans la base de donnée
    @Autowired // Injection de depandence
    ReponseRepository reponseRepository; // Un variable de type ReponseRepository


    // Portee , type de retour , nom de la fonction

    public Reponse creerReponse(Reponse reponse) {

        if (reponseRepository.findByContenueAndQuestion(reponse.getContenue(), reponse.getQuestion()) == null) {
            if(reponseRepository.countByQuestion(reponse.getQuestion()) >= 4){
                throw new DuplicateException("Nombre de reponse atteint");
            }else {
                return reponseRepository.save(reponse);
            }
        } else {
            throw new DuplicateException("Cette reponse existe déjà");
        }
    }

    public Reponse modiferReponse(Reponse reponse) {
        if (reponseRepository.findByIdReponseAndEnseignant(reponse.getIdReponse(), reponse.getEnseignant()) != null) {
            return reponseRepository.save(reponse);
        } else {
           throw new NotFoundException("Cette reponse n'existe pas");
        }
    }

    public List<Reponse> reponses() {
        if (!reponseRepository.findAll().isEmpty())
            return reponseRepository.findAll();
        else
            throw new NoContentException("Aucune reponse trouvée");
    }

    public Reponse getReponseById(int id){
        Reponse reponse = reponseRepository.findByIdReponse(id);
        if (reponse != null)
            return reponse;
        else
            throw new NotFoundException("Reponse n'existe pas");
    }

    public String supprimerReponse(Reponse reponse) {
        if (reponseRepository.findByIdReponseAndEnseignant(reponse.getIdReponse(), reponse.getEnseignant()) != null) {
            reponseRepository.delete(reponse);
            return "Succès";
        } else {
            throw new NotFoundException("Cette reponse n'existe pas");
        }
    }
}
