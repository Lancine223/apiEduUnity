package com.odk3.projet_tp_api.Service;

import com.odk3.projet_tp_api.Repository.DiscussionRepository;
import com.odk3.projet_tp_api.Repository.EnseignantRepository;
import com.odk3.projet_tp_api.Repository.EtudiantRepository;
import com.odk3.projet_tp_api.Repository.ForumRepository;
import com.odk3.projet_tp_api.model.Discussion;
import com.odk3.projet_tp_api.model.DiscussionPodjo;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service
public class DiscussionPodjoService {
    @Autowired
    private DiscussionService discussionService;
    @Autowired
    private DiscussionRepository discussionRepository;
    @Autowired
    private ForumRepository forumRepository;
    @Autowired
    private EnseignantRepository enseignantRepository;

    @Autowired
    private EtudiantRepository etudiantRepository;
    public DiscussionPodjoService(DiscussionService discussionService,
                                DiscussionRepository discussionRepository,
                                EtudiantRepository etudiantRepository,
                                EnseignantRepository enseignantRepository,
                                ForumRepository forumRepository){
        this.discussionService=discussionService;
        this.enseignantRepository = enseignantRepository;
        this.discussionRepository = discussionRepository;
        this.forumRepository = forumRepository;
        this.etudiantRepository = etudiantRepository;
    }
    public String createDiscussionPodjo(DiscussionPodjo discussionPodjo){

            Discussion discussion = new Discussion();
            discussion.setMessage(discussionPodjo.getMessage());

            discussion.setForum(forumRepository.findByIdForum(discussionPodjo.getForum()));
            if(discussionPodjo.getEnseignant() == 0){
                discussion.setEnseignant(null);

            }else {
                discussion.setEnseignant(enseignantRepository.findByIdEnseignant(discussionPodjo.getEnseignant()));
            }

            if(discussionPodjo.getEtudiant() == 0){
                discussion.setEtudiant(null);

            }else {
                discussion.setEtudiant(etudiantRepository.findByIdEtudiant(discussionPodjo.getEtudiant()));
            }
            discussionRepository.save(discussion);
            return "Inscription reussit avec succès";

    }
}
