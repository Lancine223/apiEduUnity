package com.odk3.projet_tp_api.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.odk3.projet_tp_api.Service.EnseignantPodjoService;
import com.odk3.projet_tp_api.Service.EnseignantService;
import com.odk3.projet_tp_api.exception.DuplicateException;
import com.odk3.projet_tp_api.model.Enseignant;
import com.odk3.projet_tp_api.model.EnseignantPodjo;
import com.odk3.projet_tp_api.model.Videos;
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
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@Valid
//@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/enseignant")
public class EnseignantController {
    @Autowired
    EnseignantService enseignantService;
    @Autowired
    EnseignantPodjoService enseignantpodjoservice;


    @PostMapping("/create")
    @Operation(summary = "Création d'un Enseignant")
    public ResponseEntity<Enseignant> createEnseignant(
            @Valid @RequestParam("enseignant") String enseignantString,
            @RequestParam(value ="diplome", required=false) MultipartFile multipartFile) throws Exception {

        Enseignant enseignant = new Enseignant();
        try{
            enseignant = new JsonMapper().readValue(enseignantString, Enseignant.class);
        }catch(JsonProcessingException e){
            throw new Exception(e.getMessage());
        }
        Enseignant savedUser = enseignantService.createEnseignant(enseignant,multipartFile);

        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    // Dans votre méthode de gestion des exceptions
    @ExceptionHandler(DuplicateException.class)
    public ResponseEntity<String> handleDuplicateException(DuplicateException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
    @PostMapping("/podjocreate")
    @Operation(summary = "Création d'un Enseignant")
    public String createEnseignantPodjofonction(
            @Valid @RequestParam("enseignantpodjo") String enseignantpodjoString,
            @RequestParam(value ="diplome", required=false) MultipartFile multipartFile) throws Exception {

        EnseignantPodjo enseignantpodjo = new EnseignantPodjo();
        try {
            enseignantpodjo = new JsonMapper().readValue(enseignantpodjoString, EnseignantPodjo.class);
        } catch (JsonProcessingException e) {
            throw new Exception(e.getMessage());
        }

        try {
            enseignantpodjoservice.createEnseignantPodjo(enseignantpodjo, multipartFile);
            return "succes";
        } catch (DuplicateException ex) {
            return ex.getMessage();
        }
    }


    //////////////////////////////////////////
    //////////////////////////

    @GetMapping("/read")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Succes",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Videos.class))
            }),
            @ApiResponse(responseCode = "400",description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "404",description = "Enseignant introuvable", content = @Content),
            @ApiResponse(responseCode = "500",description = "Erreur server", content = @Content)
    })
    @Operation(summary = "Affichage de la  liste des EnseignantS")
    public ResponseEntity<List<Enseignant>> getEnseignant(){
        return new ResponseEntity<>(enseignantService.getAllEnseignant(),HttpStatus.OK);}
    @GetMapping("/read/{id}")
    @Operation(summary = "Affichage  d'un Enseignant")
    public ResponseEntity<Enseignant> getEnseignantById(@Valid @PathVariable int id){
        return new ResponseEntity<>(enseignantService.getEnseignantById(id),HttpStatus.OK) ;
    }

    @PutMapping("/changeAccess/{idEnseignant}")
    public ResponseEntity<String> tochangeAccess(@PathVariable int idEnseignant) {
        enseignantService.changeAccess(idEnseignant);
        return ResponseEntity.ok("Succès : Accès de l'enseignant modifié");
    }

    @Operation(summary = "Modifier un Enseignant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Enseignant modifier",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Enseignant.class))
            }),
            @ApiResponse(responseCode = "400",description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "404",description = "Enseignant n'existe pas", content = @Content),
            @ApiResponse(responseCode = "500",description = "Erreur server", content = @Content)
    })
    @PutMapping("/modifier")
    public  Object modifierEnseignant(@Valid @RequestBody Enseignant enseignant) {
        return enseignantService.modifierEnseignant(enseignant);
    }

    @GetMapping("/list/{idClasse}")
    @Operation(summary = "Affichage la liste  des enseignants à travers l'id de la classe")
    public ResponseEntity<List<Enseignant>> ListEnseignants(@PathVariable int idClasse){
        return  new ResponseEntity<>(enseignantService.ListEnparClasse(idClasse),HttpStatus.OK);
    }


    @DeleteMapping("/supprimer")
    public String supprimerEnseignant(@Valid @RequestBody Enseignant enseignant) {
        return enseignantService.supprimer(enseignant);
    }


    @PostMapping("/login")
    @Operation(summary = "Connexion d'un Enseignant")
    public Object connexion(@RequestParam("email") String email,
                            @RequestParam("motDePasse") String motDePasse) {
        return enseignantService.connectionEnseignant(email, motDePasse);
    }

}
