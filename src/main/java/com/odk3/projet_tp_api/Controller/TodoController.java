package com.odk3.projet_tp_api.Controller;

import com.odk3.projet_tp_api.Service.TodoListService;
import com.odk3.projet_tp_api.model.Apropos;
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

    @Operation(summary = "Inserer un TodoList")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "TodoList inserer",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = TodoList.class))
            }),
            @ApiResponse(responseCode = "400",description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "409",description = "TodoList exist déjà", content = @Content),
            @ApiResponse(responseCode = "500",description = "Erreur server", content = @Content)
    })
    @PostMapping("/add")
    public Object ajouterTodoList(@Valid @RequestBody TodoList todoList){
        return todoListService.creerTodoList(todoList);
    }


    @Operation(summary = "Modifier TodoList d'un etudiant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "TodoList modifier",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Apropos.class))
            }),
            @ApiResponse(responseCode = "400",description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "404",description = "TodoList n'existe pas", content = @Content),
            @ApiResponse(responseCode = "500",description = "Erreur server", content = @Content)
    })
    @PutMapping("/modifier")
    public  Object modifierTodoList(@Valid @RequestBody TodoList todoList) {
        return todoListService.modifierTodoList(todoList);
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
