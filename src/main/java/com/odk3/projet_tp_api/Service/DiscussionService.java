package com.odk3.projet_tp_api.Service;

import com.odk3.projet_tp_api.Repository.DiscussionRepository;
import com.odk3.projet_tp_api.exception.DuplicateException;
import com.odk3.projet_tp_api.exception.NotFoundException;
import com.odk3.projet_tp_api.model.Discussion;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscussionService {
    @Autowired
    DiscussionRepository discussionRepository;

    public List<Discussion> ListDiscussionParForum(int idForum){

        // Obtention de tous les budget dans la base de donnÃ©es
        List<Discussion> ListDiscussions = discussionRepository.findByForumIdForum(idForum);

        // Si la liste est vide, le systÃ¨me lÃ¨vera une exception
        if (ListDiscussions.isEmpty())
            throw new EntityNotFoundException("Aucune discussion trouvé");

        // Dans le cas contraire le systÃ¨me retourne la liste
        return ListDiscussions;
    }

    public Discussion creerDiscussion(Discussion discussion){
        if (discussionRepository.findByIdDiscussion(discussion.getIdDiscussion()) == null) {
            return discussionRepository.save(discussion);
        } else {
            throw new DuplicateException("Cet discussion existe déjà");
        }

    }

    public Discussion modifierDiscusssion(Discussion discussion) {

        if (discussionRepository.findByIdDiscussion(discussion.getIdDiscussion()) != null){
            return discussionRepository.save(discussion);
        }
        else {
            throw  new NotFoundException("Cette discussion n'existe pas");
        }

    }
    //++++++++++++++++++++++++

    //+++++++++
    public String supprimeDiscussion(Discussion discussion) {

        if (discussionRepository.findByIdDiscussion(discussion.getIdDiscussion()) != null) {
            discussionRepository.delete(discussion);
            return "Succès";
        } else {
            throw  new NotFoundException("Cette discussion n'existe pas");
        }

    }
}
