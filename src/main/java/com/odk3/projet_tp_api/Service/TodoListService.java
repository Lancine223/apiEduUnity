package com.odk3.projet_tp_api.Service;

import com.odk3.projet_tp_api.Repository.TodoListRepository;
import com.odk3.projet_tp_api.exception.DuplicateException;
import com.odk3.projet_tp_api.exception.NotFoundException;
import com.odk3.projet_tp_api.model.TodoList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoListService {
    // Pour acceder a la table utilisateur dans la base de donnée
    @Autowired // Injection de dependance
            TodoListRepository todoListRepository; // Un variable de type UtilisateurRepository



    public TodoList creerTodoList(TodoList todoList){
        if (todoListRepository.findByIdTodoList(todoList.getIdTodoList()) == null) {

            return todoListRepository.save(todoList);
        } else {
            throw new DuplicateException("Cette tache existe déjà");
        }

    }

    public TodoList modifierTodoList(TodoList todoList) {

        if (todoListRepository.findByIdTodoList(todoList.getIdTodoList()) != null){
            return todoListRepository.save(todoList);
        }
        else {
            throw  new NotFoundException("Cette tache n'existe pas");
        }
    }


    public List<TodoList> ListParEtudiant(int idEtudiant){
        if (!todoListRepository.findByEtudiantIdEtudiant(idEtudiant).isEmpty()){
            return todoListRepository.findByEtudiantIdEtudiant(idEtudiant);
        }else {
            throw new NotFoundException("Aucune tâche disponible");
        }
    }






    public String supprimerTodo(TodoList todoList) {
        if (todoListRepository.findByIdTodoList(todoList.getIdTodoList()) != null) {

            todoListRepository.delete(todoList);
            return "Succès";
        } else {
            throw  new NotFoundException("Cette tache n'existe pas");
        }

    }
}
