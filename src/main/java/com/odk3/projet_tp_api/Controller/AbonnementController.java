package com.odk3.projet_tp_api.Controller;

import com.odk3.projet_tp_api.Service.AbonnementService;
import com.odk3.projet_tp_api.model.Abonnement;
import com.odk3.projet_tp_api.model.Utilisateur;
import com.odk3.projet_tp_api.model.Videos;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
//@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
@RequestMapping("/abonnement")
public class AbonnementController {
    @Autowired
    AbonnementService abonnementService;

    @Operation(summary = "faire un abonnement")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Abonnement effectuer",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Utilisateur.class))
            }),
            @ApiResponse(responseCode = "400",description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "409",description = "Vous êtes déjà abonné", content = @Content),
            @ApiResponse(responseCode = "500",description = "Erreur server", content = @Content)
    })
    @PostMapping("/abonner")
    public Object ajouterAbonnement(@Valid @RequestBody Abonnement abonnement){
        return abonnementService.creerAbonnement(abonnement);
    }
    @GetMapping("/list/{idEtudiant}")
    @Operation(summary = "Affichage la liste  des abonnements à travers l'id de l'etudiant")
    public ResponseEntity<List<Abonnement>> listeAbonnementByEtudiant(@PathVariable int idEtudiant){
        return  new ResponseEntity<>(abonnementService.ListabonneParEtudiant(idEtudiant),HttpStatus.OK);
    }


    @PostMapping("/etudiant/{idEtudiant}")
    public ResponseEntity<Abonnement> createAbonnementForEtudiant(@PathVariable int idEtudiant, @RequestBody Abonnement abonnement) {
        Abonnement createdAbonnement = abonnementService.createAbonnementForEtudiant(idEtudiant, abonnement);
        return new ResponseEntity<>(createdAbonnement, HttpStatus.CREATED);
    }
// Best pratique
@PostMapping("/best/{idEtudiant}")
public ResponseEntity<Abonnement> bestAbonnementForEtudiant(
        @PathVariable int idEtudiant,
        @RequestBody Abonnement abonnement) {

    Abonnement newAbonnement = abonnementService.BestPratiquePourAbonner(idEtudiant, abonnement);

    return new ResponseEntity<>(newAbonnement, HttpStatus.CREATED);
}

    @Operation(summary = "Desabonner ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Desabonner avec succès",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Videos.class))
            }),
            @ApiResponse(responseCode = "400",description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "404",description = "Abonnement introuvable", content = @Content),
            @ApiResponse(responseCode = "500",description = "Erreur server", content = @Content)
    })
    @DeleteMapping("/supprimer")
    public String seDesabonner(@Valid @RequestBody Abonnement abonnement) {
        return abonnementService.supprimeAbonnement(abonnement);
    }
    ////////////////////////////
    @GetMapping("/totalAbonnes")
    public int getTotalAbonnes() {
        return abonnementService.countTotalAbonne();
    }

    @Operation(summary = "Renvoie la liste des abonnement")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "List renvoyer",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Abonnement.class))
            }),
            @ApiResponse(responseCode = "400",description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "204",description = "List vide", content = @Content),
            @ApiResponse(responseCode = "500",description = "Erreur server", content = @Content)
    })
    @GetMapping("/list")
    public List<Abonnement> allAbonnements(){
        return abonnementService.listAbonnements();
    }

    @GetMapping("/totalMontantAbonnements")
    public int getTotalMontantAbonnements() {
        return abonnementService.getTotalMontantAbonnements();
    }


}
