package com.odk3.projet_tp_api.Service;

import com.odk3.projet_tp_api.Repository.ForumRepository;
import com.odk3.projet_tp_api.model.Forum;
import com.odk3.projet_tp_api.model.Niveau;
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
}
