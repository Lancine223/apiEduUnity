package com.odk3.projet_tp_api.Repository;

import com.odk3.projet_tp_api.model.Participer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParticiperRepository extends JpaRepository<Participer,Integer> {

    Participer findByIdParticiper(int id);
    Participer findByEtudiantIdEtudiantAndQuizIdQuiz(int idEtudiant, int idQuiz);
    List<Participer> findByQuizIdQuizOrderByScoreDesc(int idQuiz);
}
