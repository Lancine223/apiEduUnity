package com.odk3.projet_tp_api.Service;

import com.odk3.projet_tp_api.Repository.DiscussionRepository;
import com.odk3.projet_tp_api.model.Discussion;
import com.odk3.projet_tp_api.model.Forum;
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
}
