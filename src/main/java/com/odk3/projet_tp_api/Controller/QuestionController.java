package com.odk3.projet_tp_api.Controller;

import com.odk3.projet_tp_api.Repository.QuestionRepository;
import com.odk3.projet_tp_api.Service.QuestionService;
import com.odk3.projet_tp_api.model.Question;
import com.odk3.projet_tp_api.model.Quiz;
import com.odk3.projet_tp_api.model.Utilisateur;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    QuestionService questionService;


    // =====================================================================================================

    @Operation(summary = "Ajouter des question ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Question ajouter",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Question.class))
            }),
            @ApiResponse(responseCode = "400",description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "409",description = "Qustion existe déjà", content = @Content),
            @ApiResponse(responseCode = "500",description = "Erreur server", content = @Content)
    })

    @PostMapping("/ajouter")
    public ResponseEntity<Question> ajouterQuestion(@Valid @RequestBody Question question){
        return new ResponseEntity<>(questionService.createQuestion(question), HttpStatus.OK);
    }

    // =====================================================================================================

    @Operation(summary = "Modifier une Question ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Question modifier",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Question.class))
            }),
            @ApiResponse(responseCode = "400",description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "404",description = "introuvable", content = @Content),
            @ApiResponse(responseCode = "500",description = "Erreur server", content = @Content)
    })

    @PutMapping("/modifier")
    public ResponseEntity<Object> modifierQuestion(@Valid @RequestBody Question question){
        return new ResponseEntity<>(questionService.updateQuestion(question), HttpStatus.OK);
    }



    // =====================================================================================================

    @Operation(summary = "List des Questions ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Question list",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Question.class))
            }),
            @ApiResponse(responseCode = "400",description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "204",description = "List vite", content = @Content),
            @ApiResponse(responseCode = "500",description = "Erreur server", content = @Content)
    })

    @GetMapping("/list")
    public ResponseEntity<List<Question>> listQuestion(){
        return new ResponseEntity<>(questionService.getAllQuestions(),HttpStatus.OK);
    }


    @Operation(summary = "Recuper une question à travers son id ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Question trouvée",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Question.class))
            }),
            @ApiResponse(responseCode = "400",description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "404",description = "Question non trouvée", content = @Content),
            @ApiResponse(responseCode = "500",description = "Erreur server", content = @Content)
    })
    @GetMapping("/affiche/{id}")
    public ResponseEntity<Question> afficheQuestionParId(@PathVariable int id){
        return new ResponseEntity<>(questionService.getQuestionById(id),HttpStatus.OK);
    }



    // =====================================================================================================

    @Operation(summary = "Supprimer une question ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Question supprimer",content = {
                    @Content(mediaType = "text/plain",schema = @Schema(implementation = Question.class))
            }),
            @ApiResponse(responseCode = "400",description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "404",description = "Question existe pas", content = @Content),
            @ApiResponse(responseCode = "500",description = "Erreur server", content = @Content)
    })

    @DeleteMapping("/supprimer")
    public ResponseEntity<String> supprimerQuestion(@Valid @RequestBody Question question){

        return new ResponseEntity<>(questionService.deleteQuestion(question),HttpStatus.OK);
    }


}
