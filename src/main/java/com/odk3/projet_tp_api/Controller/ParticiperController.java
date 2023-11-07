package com.odk3.projet_tp_api.Controller;

import com.odk3.projet_tp_api.Service.ParticiperService;
import com.odk3.projet_tp_api.model.Participer;
import com.odk3.projet_tp_api.model.Question;
import io.swagger.v3.oas.annotations.Operation;
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

import java.util.List;

@RestController
//@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("participer")
public class ParticiperController {

    @Autowired // injection de donnée
    ParticiperService participerService;


    // =====================================================================================================

    @Operation(summary = "Ajouter une participation ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Participer ajouter", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Participer.class))
            }),
            @ApiResponse(responseCode = "400", description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "409", description = "Qustion existe déjà", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erreur server", content = @Content)
    })

    @PostMapping("/ajouter")
    public ResponseEntity<Object> ajouterParticiper(@Valid @RequestBody Participer participer) {

        Participer verifParticiper = participerService.creerParticiper(participer);
        if (verifParticiper != null) {
            return new ResponseEntity<>(verifParticiper, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("existe pas", HttpStatus.NOT_FOUND);
        }
    }


    // =====================================================================================================

    @Operation(summary = "List des Participation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Participer list", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Participer.class))
            }),
            @ApiResponse(responseCode = "400", description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "204", description = "List vide", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erreur server", content = @Content)
    })

    @GetMapping("/list")
    public List<Participer> listParticiper() {
        return participerService.participerList();
    }


    // =====================================================================================================

    @Operation(summary = "Modifier une participation ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Question modifier", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Participer.class))
            }),
            @ApiResponse(responseCode = "400", description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "404", description = "introuvable", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erreur server", content = @Content)
    })

    @PutMapping("/modifier")
    public ResponseEntity<Object> modifierParticiper(@RequestBody Participer participer) {
        Participer verifParticiper = participerService.modifierParticiper(participer);
        if (verifParticiper != null) {
            return new ResponseEntity<>(verifParticiper, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("existe pas", HttpStatus.NOT_FOUND);
        }
    }


    // =====================================================================================================

    @Operation(summary = "Supprimer une participer ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Participer supprimer", content = {
                    @Content(mediaType = "text/plain", schema = @Schema(implementation = Participer.class))
            }),
            @ApiResponse(responseCode = "400", description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "404", description = "Question existe pas", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erreur server", content = @Content)
    })

    @DeleteMapping("/supprimer")
    public ResponseEntity<String> supprimerParticiper(@RequestBody Participer participer) {
        String message = participerService.supprimerParticiper(participer);

        if (message.equals("Sussès")) {
            return new ResponseEntity<>("Suppression avec succès", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("NOT found", HttpStatus.NOT_FOUND);
        }
    }

    //=======================================JOUER=================================================

    @GetMapping("/")
    public ResponseEntity<List<String>> listDesQuizs() {
        return new ResponseEntity<>(participerService.allQuizs(), HttpStatus.OK);
    }

    @GetMapping("/{idEtudiant}/{idQuiz}/jouer")
    public ResponseEntity<List<String>> jouer(@PathVariable int idEtudiant, @PathVariable int idQuiz, @RequestParam(value = "choix",required = false) Integer choix) {

        if (choix == null) {
            return new ResponseEntity<>(participerService.jouer(idEtudiant, idQuiz), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(participerService.answer(idEtudiant, idQuiz, choix), HttpStatus.OK);
        }
    }

    @GetMapping("/{idEtudiant}/{idQuiz}/classement")
    public List<String> classement(@PathVariable int idUser, @PathVariable int idQuiz){
        return participerService.rang(idUser,idQuiz);
    }

}
