package com.odk3.projet_tp_api.Repository;

import com.odk3.projet_tp_api.model.Enseignant;
import com.odk3.projet_tp_api.model.Question;
import com.odk3.projet_tp_api.model.Quiz;
import com.odk3.projet_tp_api.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Integer> {
    Question findQuestionByIdQuestionAndEnseignant(int id, Enseignant enseignant);
    Question findByIdQuestion(int id);
    Question findQuestionByContenueAndEnseignantAndQuiz(String contenue, Enseignant enseignant, Quiz quiz);

}
