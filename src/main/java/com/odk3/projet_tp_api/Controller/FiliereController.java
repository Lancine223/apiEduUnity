package com.odk3.projet_tp_api.Controller;

import com.odk3.projet_tp_api.Service.FiliereService;
import com.odk3.projet_tp_api.model.Filiere;
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
@CrossOrigin
@RestController
//@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/filiere")
public class FiliereController {
    @Autowired
    FiliereService filiereService;


    @Operation(summary = "Inserer un filiere")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "filiere inserer",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Filiere.class))
            }),
            @ApiResponse(responseCode = "400",description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "409",description = "Filiere exist déjà", content = @Content),
            @ApiResponse(responseCode = "500",description = "Erreur server", content = @Content)
    })
    @PostMapping("/add")
    public Object ajouterFiliere(@Valid @RequestBody Filiere  filiere){
        return filiereService.creerFiliere(filiere);
    }


    @Operation(summary = "Renvoie la liste des Filiere")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "List renvoyer",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Filiere.class))
            }),
            @ApiResponse(responseCode = "400",description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "204",description = "List vide", content = @Content),
            @ApiResponse(responseCode = "500",description = "Erreur server", content = @Content)
    })
    @GetMapping("/list")
    public List<Filiere> allFilieres(){
        return filiereService.listFilieres();
    }

    @GetMapping("/list/{idNiveau}")
    @Operation(summary = "Affichage la liste  des Filieres à travers l'id niveau")
    public ResponseEntity<List<Filiere>> listeByNiveau(@PathVariable int idNiveau){
        return  new ResponseEntity<>(filiereService.ListFiliereParNiveau(idNiveau), HttpStatus.OK);
    }

    @GetMapping("/read/{idFiliere}")
    public ResponseEntity<Filiere> getOneFiliere(@PathVariable int idFiliere) {
        Filiere filiere = filiereService.getFiliereById(idFiliere);
        if(filiere != null) {
            return ResponseEntity.ok(filiere); // Retourner les détails du niveau
        } else {
            return ResponseEntity.notFound().build(); // Gérer le cas où le niveau n'est pas trouvé
        }
    }


    @Operation(summary = "Modifier un Filiere")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Filiere modifier",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Filiere.class))
            }),
            @ApiResponse(responseCode = "400",description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "404",description = "Filiere n'existe pas", content = @Content),
            @ApiResponse(responseCode = "500",description = "Erreur server", content = @Content)
    })
    @PutMapping("/modifier")
    public  Object modifierFiliere(@Valid @RequestBody Filiere filiere) {
        return filiereService.modifierFiliere(filiere);
    }


    @Operation(summary = "Supprimer un Filiere")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Filiere supprimer",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Filiere.class))
            }),
            @ApiResponse(responseCode = "400",description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "404",description = "Filiere introuvable", content = @Content),
            @ApiResponse(responseCode = "500",description = "Erreur server", content = @Content)
    })
    @DeleteMapping("/supprimer")
    public String supprimerFiliere(@Valid @RequestBody Filiere filiere) {
        return filiereService.supprimeFiliere(filiere);
    }

}
