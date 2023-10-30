package com.odk3.projet_tp_api.Controller;

import com.odk3.projet_tp_api.Repository.ReponseRepository;
import com.odk3.projet_tp_api.Service.ReponseService;
import com.odk3.projet_tp_api.model.Question;
import com.odk3.projet_tp_api.model.Quiz;
import com.odk3.projet_tp_api.model.Reponse;
import com.odk3.projet_tp_api.model.Utilisateur;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reponse")
public class ReponseController {

    @Autowired // Injection de depandence
    ReponseService reponseService; // Un variable de type UtilisateurService;


    @Operation(summary = "Ajouter une reponse")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Reponse ajouter",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Reponse.class))
            }),
            @ApiResponse(responseCode = "400",description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "409",description = "reponse exist déjà", content = @Content),
            @ApiResponse(responseCode = "500",description = "Erreur server", content = @Content)
    })
    @PostMapping ("/ajouter")
    public ResponseEntity<Reponse> ajouterReponse(@Valid @RequestBody Reponse reponse) {
        return new ResponseEntity<>(reponseService.creerReponse(reponse),HttpStatus.OK);
    }


    @Operation(summary = "Modifier une reponse")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Reponse modifier",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Reponse.class))
            }),
            @ApiResponse(responseCode = "400",description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "404",description = "Reponse n'existe pas", content = @Content),
            @ApiResponse(responseCode = "500",description = "Erreur server", content = @Content)
    })
    @PutMapping("/modifier")
    public ResponseEntity<Object> modifierReponse(@Valid @RequestBody Reponse reponse) {
        return new ResponseEntity<>(reponseService.modiferReponse(reponse), HttpStatus.OK);
    }


    @Operation(summary = "Renvoie la liste des reponses")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "List renvoyer",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Reponse.class))
            }),
            @ApiResponse(responseCode = "400",description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "204",description = "List vide", content = @Content),
            @ApiResponse(responseCode = "500",description = "Erreur server", content = @Content)
    })
    @GetMapping("list")
    public ResponseEntity<List<Reponse>> listQuestion(){
        return new ResponseEntity<>(reponseService.reponses(),HttpStatus.OK);
    }


    @Operation(summary = "Supprimer une reponse")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Reponse supprimer",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Reponse.class))
            }),
            @ApiResponse(responseCode = "400",description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "404",description = "Utilisateur introuvable", content = @Content),
            @ApiResponse(responseCode = "500",description = "Erreur server", content = @Content)
    })
    @DeleteMapping("/supprimer")
    public  ResponseEntity<String> supprimerReponse(@Valid @RequestBody Reponse reponse){
        return new ResponseEntity<>(reponseService.supprimerReponse(reponse), HttpStatus.OK);
    }

}
