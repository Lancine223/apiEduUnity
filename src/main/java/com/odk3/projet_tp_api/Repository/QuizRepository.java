package com.odk3.projet_tp_api.Repository;

import com.odk3.projet_tp_api.model.Enseignant;
import com.odk3.projet_tp_api.model.Quiz;
import com.odk3.projet_tp_api.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Integer> {

    public Quiz findByTitreAndEnseignant(String titre, Enseignant enseignant);

    public  Quiz findByIdQuizAndEnseignant(int id, Enseignant enseignant);

    public List<Quiz> findByTitreContains(String cleTitre);
    public List<Quiz> findByIdEnseignant(int idEnseignant);

    Quiz findByIdQuiz(int id);
}
