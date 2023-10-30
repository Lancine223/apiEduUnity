package com.odk3.projet_tp_api.Service;

import com.odk3.projet_tp_api.Repository.EtudiantRepository;
import com.odk3.projet_tp_api.Repository.ParticiperRepository;
import com.odk3.projet_tp_api.Repository.QuizRepository;
import com.odk3.projet_tp_api.Repository.UtilisateurRepository;
import com.odk3.projet_tp_api.exception.NoContentException;
import com.odk3.projet_tp_api.exception.NotFoundException;
import com.odk3.projet_tp_api.model.Participer;
import com.odk3.projet_tp_api.model.Question;
import com.odk3.projet_tp_api.model.Quiz;
import com.odk3.projet_tp_api.model.Reponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ParticiperService {

    @Autowired // injection de dependance
    ParticiperRepository participerRepository;

    @Autowired
    QuizRepository quizRepository;

    @Autowired
    EtudiantRepository etudiantRepository;

    public Participer creerParticiper(Participer participer) {
        if (participerRepository.findByIdParticiper(participer.getIdParticiper()) == null) {
            participerRepository.save(participer);
            return participerRepository.findByIdParticiper(participer.getIdParticiper());
        } else {
            return null;
        }
    }

    public List<Participer> participerList() {
        return participerRepository.findAll();
    }

    public Participer modifierParticiper(Participer participer) {
        if (participerRepository.findByIdParticiper(participer.getIdParticiper()) != null) {
            participerRepository.save(participer);
            return participerRepository.findByIdParticiper(participer.getIdParticiper());
        } else {
            return null;
        }
    }

    public String supprimerParticiper(Participer participer) {
        if (participerRepository.findByIdParticiper(participer.getIdParticiper()) != null) {
            participerRepository.delete(participer);
            return "Sussès";
        } else {
            return "exite pas";
        }
    }

    //===========================================JOUER==========================================

    public List<String> allQuizs(){
        List<String> quizNames = new ArrayList<>();
        List<Quiz> quizList = quizRepository.findAll();
        quizNames.add("Choisissez le numéro d'un quiz pour commencer");
        quizList.forEach(quiz -> {
            quizNames.add(quizNames.size()+" : "+quiz.getTitre());
        });
        if (!quizNames.isEmpty())
            return quizNames;
        else
            throw new NoContentException("Aucun quiz trouvé");
    }

    public List<String> jouer(int idEtudiant, int idQuiz){
        List<String> list = new ArrayList<>();
        if(idQuiz <=0 || idQuiz > quizRepository.findAll().size()){
            list.add("Choisissez un bon numéro");
            return list;
        }
        Participer participer = participerRepository.findByEtudiantIdEtudiantAndQuizIdQuiz(idEtudiant,
                quizRepository.findAll().get(idQuiz-1).getIdQuiz());
        if (participer == null){
            Participer participer1 = new Participer();
            participer1.setEtudiant(etudiantRepository.findByIdEtudiant(idEtudiant));
            participer1.setQuiz(quizRepository.findByIdQuiz(quizRepository.findAll().get(idQuiz-1).getIdQuiz()));
            participer1.setScore(0);
            participer1.setLevel(1);
            participer1.setTerminer(false);
            participerRepository.save(participer1);
            Question question = quizRepository.findByIdQuiz(quizRepository.findAll().get(idQuiz-1).getIdQuiz()).getQuestions().get(0);
            list.add("Choisissez la bonne réponse");
            list.add(question.getContenue());
            list.add("");
            question.getReponses().forEach(reponse -> {
                list.add(list.size()-2+" : "+reponse.getContenue());
            });
            return list;
        }else {
            if (participer.isTerminer()){
                list.add("Vous avez déjà terminer cet quiz, voulez-vous le réjouer");
                return list;
            }
            Question question = quizRepository.findByIdQuiz(quizRepository.findAll().get(idQuiz-1).getIdQuiz()).getQuestions().get(participer.getLevel()-1);
            list.add("Choisissez la bonne réponse");
            list.add(question.getContenue());
            list.add("");
            question.getReponses().forEach(reponse -> {
                list.add(list.size()-2+" : "+reponse.getContenue());
            });
            return list;
        }
    }

    public List<String> answer(int idEtudiant, int idQuiz,int choix){
        List<String> list = new ArrayList<>();
        Participer participer = participerRepository.findByEtudiantIdEtudiantAndQuizIdQuiz(idEtudiant,
                quizRepository.findAll().get(idQuiz-1).getIdQuiz());
        if (participer == null)
            throw new NotFoundException("Vous n'avez aucune participation pour cet quiz");
        if (participer.isTerminer()){
            list.add("Desolé vous avez terminé cet quiz");
            return list;
        }
        Question question = quizRepository.findByIdQuiz(quizRepository.findAll().get(idQuiz-1).getIdQuiz()).getQuestions().get(participer.getLevel()-1);
        if (choix <=0 || choix > question.getReponses().size()){
            list.add("Veuillez choisir un numero correct");
            return list;
        }
        Reponse reponse = question.getReponses().get(choix-1);
        if (reponse.isCorrect()){
            List<Question> questions = quizRepository.findByIdQuiz(quizRepository.findAll().get(idQuiz-1).getIdQuiz()).getQuestions();
            if (questions.size()>= participer.getLevel()+1){
                participer.setScore(participer.getScore()+10);
                participer.setLevel(participer.getLevel()+1);
                participerRepository.save(participer);
                question = quizRepository.findByIdQuiz(quizRepository.findAll().get(idQuiz-1).getIdQuiz()).getQuestions().get(participer.getLevel()-1);
                list.add("Bravo vous avez choisie la bonne réponse, votre score est de "+participer.getScore()+" points ");
                list.add(" Voici la question suivante ");
                list.add(question.getContenue());
                list.add("");
                question.getReponses().forEach(repon -> {
                    list.add(list.size()-3+" : "+repon.getContenue());
                });
                return list;
            }else {
                participer.setScore(participer.getScore()+10);
                participer.setTerminer(true);
                participerRepository.save(participer);
                list.add("Bravo vous avez choisie la bonne réponse, votre score est de "+participer.getScore()+" points");
                list.add("Le jeu est terminé votre score final est de "+participer.getScore()+" points");
                return list;
            }

        }else {
            List<Question> questions = quizRepository.findByIdQuiz(quizRepository.findAll().get(idQuiz-1).getIdQuiz()).getQuestions();
            if (questions.size()>= participer.getLevel()+1){
                participer.setLevel(participer.getLevel()+1);
                participerRepository.save(participer);
                Question questionNext = quizRepository.findByIdQuiz(quizRepository.findAll().get(idQuiz-1).getIdQuiz()).getQuestions().get(participer.getLevel()-1);
                question.getReponses().forEach(repons -> {
                    if (repons.isCorrect())
                        list.add("Vous n'avez pas eu la bonne réponse. C'était : "+repons.getContenue()+
                                " votre score est : "+participer.getScore()+" points");
                });
                list.add("Voici la question suivante");
                list.add(questionNext.getContenue());
                list.add("");
                questionNext.getReponses().forEach(repon -> {
                    list.add(list.size()-3+" : "+repon.getContenue());
                });
                return list;
            }else {
                participer.setTerminer(true);
                participerRepository.save(participer);
                question.getReponses().forEach(repons -> {
                    if (repons.isCorrect())
                        list.add("Vous n'avez pas eu la bonne réponse. C'était : "+repons.getContenue()+
                                "votre score est : "+participer.getScore()+" points");
                });
                list.add("Le jeu est terminé votre score final est "+participer.getScore()+" points");
                return list;
            }

        }
    }

    public List<String> rang(int idEtudiant, int idQuiz){
        Participer participer = participerRepository.findByEtudiantIdEtudiantAndQuizIdQuiz(idEtudiant,
                quizRepository.findAll().get(idQuiz-1).getIdQuiz());
        if (participer != null){
            AtomicInteger i = new AtomicInteger();
            List<String> list = new ArrayList<>();
            List<Participer> classement = participerRepository.findByQuizIdQuizOrderByScoreDesc(quizRepository.findAll().get(idQuiz-1).getIdQuiz());
            classement.forEach(participer1 -> {
                i.getAndIncrement();
                if(participer1.getEtudiant().getIdEtudiant() == idEtudiant){
                    list.add("Voici le classement du quiz et vous êtes "+i.get()+(i.get() == 1 ? "er(ère)" : "ème")+" avec "+participer.getScore()+" points");
                }
            });
            classement.forEach(participer1 -> {
                list.add(list.size()+" : "+participer1.getEtudiant().getNom()+" "+participer1.getEtudiant().getPrenom()+" avec "+participer1.getScore()+" points");
            });
            return list;
        }else
            throw new NotFoundException("Aucune participation trouvé");
    }

}


