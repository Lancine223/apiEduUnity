package com.odk3.projet_tp_api.Controller;

import com.odk3.projet_tp_api.Service.TodoListService;
import com.odk3.projet_tp_api.model.Quiz;
import com.odk3.projet_tp_api.model.TodoList;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@Valid
@RequestMapping("/todo")
public class TodoController {
    @Autowired
    TodoListService todoListService;
    @PutMapping("/complete/{idTodoList}")
    public ResponseEntity<String> toAccomplit(@PathVariable int idTodoList) {
        todoListService.AcomplitTache(idTodoList);
        return ResponseEntity.ok("Succès : tache modifie avec succès");
    }

    @Operation(summary = "Ajouter des taches ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Tache ajouter",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Quiz.class))
            }),
            @ApiResponse(responseCode = "400",description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "409",description = "Tache existe déjà", content = @Content),
            @ApiResponse(responseCode = "500",description = "Erreur server", content = @Content)
    })

    @PostMapping("/ajouter")
    public ResponseEntity<Object> ajouterTache(@Valid @RequestBody TodoList todoList) {
        TodoList verifierTodo = todoListService.creerTodo(todoList);
        if (verifierTodo != null) {
            return new ResponseEntity<>(verifierTodo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("cette tache existe dejà! ", HttpStatus.OK);
        }
    }

    @Operation(summary = "Modification des taches ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Tache supprimer",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Quiz.class))
            }),
            @ApiResponse(responseCode = "400",description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "404",description = "introuvable", content = @Content),
            @ApiResponse(responseCode = "500",description = "Erreur server", content = @Content)
    })

    @PutMapping("/modifier")
    public ResponseEntity<Object> modifierTache(@Valid @RequestBody TodoList todoList) {
        TodoList verifierTodo = todoListService.modifierTodo(todoList);
        if (verifierTodo != null) {
            return new ResponseEntity<>(verifierTodo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("cette tache n'existe pas", HttpStatus.NOT_FOUND);
        }
        //return new ResponseEntity<>(quizService.modifierQuiz(quiz), HttpStatus.OK);
    }

    @GetMapping("/list/{idEtudiant}")
    @Operation(summary = "Affichage la liste  des taches à travers l'id de l' etudiant ")
    public ResponseEntity<List<TodoList>> ListTodoLists(@PathVariable int idEtudiant){
        return  new ResponseEntity<>(todoListService.ListParEtudiant(idEtudiant),HttpStatus.OK);
    }

    @DeleteMapping("/supprimer")
    public String supprimerTodo(@Valid @RequestBody TodoList todoList) {
        return todoListService.supprimerTodo(todoList);
    }

}
