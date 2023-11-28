package com.odk3.projet_tp_api.Service;

import com.odk3.projet_tp_api.Repository.TodoListRepository;
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

    public String AcomplitTache(int id){
        TodoList todoList = todoListRepository.findByIdTodoList(id);
        if (todoList == null)
        {

            throw new NotFoundException("invalid");
        }else {
            todoList.setComplete(!todoList.isComplete());
            todoListRepository.save(todoList);
            return "succes";
        }
    }

    public TodoList creerTodo(TodoList todoList) {
        if (todoListRepository.findByDescriptionAndEtudiant(todoList.getDescription(), todoList.getEtudiant()) == null) {
            return todoListRepository.save(todoList);
        } else {
            return null;
        }
    }

    public TodoList modifierTodo(TodoList todoList) {
        if (todoListRepository.findByIdTodoListAndEtudiant(todoList.getIdTodoList(), todoList.getEtudiant()) != null) {
            return todoListRepository.save(todoList);
        } else {
            throw new NotFoundException("Cette tache n'existe pas");
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
