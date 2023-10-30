package com.odk3.projet_tp_api.Service;

import com.odk3.projet_tp_api.Repository.QuestionRepository;
import com.odk3.projet_tp_api.exception.DuplicateException;
import com.odk3.projet_tp_api.exception.NoContentException;
import com.odk3.projet_tp_api.exception.NotFoundException;
import com.odk3.projet_tp_api.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    public Question createQuestion(Question question){

        if (questionRepository.findQuestionByContenueAndEnseignantAndQuiz(question.getContenue(), question.getEnseignant(), question.getQuiz()) == null){
            return questionRepository.save(question);
        }else
            throw new DuplicateException("Cette Question existe déjà");
    }

    public Question updateQuestion(Question question){

        if(questionRepository.findQuestionByIdQuestionAndEnseignant(question.getIdQuestion(),question.getEnseignant()) != null){
            return questionRepository.save(question);
        }else
            throw new NotFoundException("Cette question n'existe pas");
    }

    public List<Question> getAllQuestions(){
        if (!questionRepository.findAll().isEmpty())
            return questionRepository.findAll();
        else
            throw new NoContentException("Aucune question retrouvée");
    }

    public Question getQuestionById(int id){
        Question question = questionRepository.findByIdQuestion(id);
        if (question != null)
            return question;
        else
            throw new NotFoundException("Cette question n'existe pas");
    }

    public String deleteQuestion(Question question){

        if(questionRepository.findQuestionByIdQuestionAndEnseignant(question.getIdQuestion(),question.getEnseignant()) != null){
            questionRepository.delete(question);
            return "succes";
        }else
            throw new NotFoundException("Cette question n'existe pas");
    }


}
