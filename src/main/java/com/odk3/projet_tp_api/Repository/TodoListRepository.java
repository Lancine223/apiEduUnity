package com.odk3.projet_tp_api.Repository;

import com.odk3.projet_tp_api.model.Etudiant;
import com.odk3.projet_tp_api.model.TodoList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TodoListRepository extends JpaRepository<TodoList, Integer> {
    public List<TodoList> findByEtudiantIdEtudiant(int idEtudiant);

    public TodoList findByDescriptionAndEtudiant(String description, Etudiant etudiant);
    public TodoList findByIdTodoList(int idTodoList);
    public TodoList findByIdTodoListAndEtudiant(int id, Etudiant etudiant);

}
