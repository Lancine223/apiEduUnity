package com.odk3.projet_tp_api.Service;

import com.odk3.projet_tp_api.Repository.ForumRepository;
import com.odk3.projet_tp_api.exception.DuplicateException;
import com.odk3.projet_tp_api.exception.NotFoundException;
import com.odk3.projet_tp_api.model.Forum;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ForumService {
    @Autowired
    ForumRepository forumRepository;

    public List<Forum> ListForumParEnseignant(int idEnseignant){

        // Obtention de tous les budget dans la base de donnÃ©es
        List<Forum> ListForums = forumRepository.findByEnseignantIdEnseignant(idEnseignant);
        // Si la liste est vide, le systÃ¨me lÃ¨vera une exception
        if (ListForums.isEmpty())
            throw new EntityNotFoundException("Aucun forum trouvé");
        // Dans le cas contraire le systÃ¨me retourne la liste
        return ListForums;
    }


    public Forum creerForum(Forum forum){
        if (forumRepository.findByIdForum(forum.getIdForum()) == null) {
            return forumRepository.save(forum);
        } else {
            throw new DuplicateException("Cet forum existe déjà");
        }

    }
    //++++++++++++++++++++++++
    public Forum modifierForum(Forum forum) {

        if (forumRepository.findByIdForum(forum.getIdForum()) != null){
            return forumRepository.save(forum);
        }
        else {
            throw  new NotFoundException("Cet forum n'existe pas");
        }

    }
    //+++++++++
    public String supprimeForum(Forum forum) {

        if (forumRepository.findByIdForum(forum.getIdForum()) != null) {
            forumRepository.delete(forum);
            return "Succès";
        } else {
            throw  new NotFoundException("Cet forum n'existe pas");
        }

    }
    public Forum getForumBydIdForum(int id){
        Forum forum = forumRepository.findByIdForum(id);
        if(forum != null)
            return forum;
        else
            throw new NotFoundException("Cet forum n'existe pas");
    }

}
