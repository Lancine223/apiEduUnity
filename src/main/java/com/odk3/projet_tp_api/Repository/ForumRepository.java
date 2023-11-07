package com.odk3.projet_tp_api.Repository;

import com.odk3.projet_tp_api.model.Enseignant;
import com.odk3.projet_tp_api.model.Forum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ForumRepository extends JpaRepository<Forum, Integer> {
    public List<Forum> findByEnseignantIdEnseignant(int idEnseignant);
}
