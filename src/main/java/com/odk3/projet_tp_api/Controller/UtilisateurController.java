package com.odk3.projet_tp_api.Controller;

import com.odk3.projet_tp_api.Service.UtilisateurService;
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

import java.util.List;

@RestController
@RequestMapping("/utilisateur")
public class UtilisateurController {


    @Autowired // Injection de dependance
    UtilisateurService utilisateurService; // Un variable de type UtilisateurService

    /*@PostMapping("/ajouter")
    public Utilisateur ajouterUtilisateur(@RequestBody Utilisateur utilisateur){
        return utilisateurService.creerUtilisateur(utilisateur);
    }*/

    @Operation(summary = "Inserer un utilisateur")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Utilisateur inserer",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Utilisateur.class))
            }),
            @ApiResponse(responseCode = "400",description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "409",description = "Utilisateur exist déjà", content = @Content),
            @ApiResponse(responseCode = "500",description = "Erreur server", content = @Content)
    })
    @PostMapping("/ajouter")
    public Object ajouterUtilisateur(@Valid @RequestBody Utilisateur utilisateur){
        return utilisateurService.creerUtilisateur(utilisateur);
    }


    @Operation(summary = "Connexion d'un utilisateur")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Utilisateur connecter",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Utilisateur.class))
            }),
            @ApiResponse(responseCode = "400",description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "404",description = "Utilisateur introuvable", content = @Content),
            @ApiResponse(responseCode = "500",description = "Erreur server", content = @Content)
    })
    @PostMapping("/connexion")
    public Object connexion(@Parameter(description = "Email de l'utilisateur") @RequestParam("email") String email,
                            @Parameter(description = "Mot de passe de l'utilisateur") @RequestParam("mot_de_passe") String mot_de_passe) {
        return utilisateurService.connectionUtilisateur(email, mot_de_passe);
    }


    @Operation(summary = "Renvoie la liste des utilisateur")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "List renvoyer",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Utilisateur.class))
            }),
            @ApiResponse(responseCode = "400",description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "204",description = "List vide", content = @Content),
            @ApiResponse(responseCode = "500",description = "Erreur server", content = @Content)
    })
    @GetMapping("/list")
    public List<Utilisateur> allUsers(){
        return utilisateurService.listUtilisateurs();
    }


    @Operation(summary = "Modifier un utilisateur")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Utilisateur modifier",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Utilisateur.class))
            }),
            @ApiResponse(responseCode = "400",description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "404",description = "Utilisateur n'existe pas", content = @Content),
            @ApiResponse(responseCode = "500",description = "Erreur server", content = @Content)
    })
    @PutMapping("/modifier")
    public  Object modifierUtilisateur(@Valid @RequestBody Utilisateur utilisateur) {
        return utilisateurService.modifierUtilisateur(utilisateur);
    }


    @Operation(summary = "Supprimer un utilisateur")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Utilisateur supprimer",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Utilisateur.class))
            }),
            @ApiResponse(responseCode = "400",description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "404",description = "Utilisateur introuvable", content = @Content),
            @ApiResponse(responseCode = "500",description = "Erreur server", content = @Content)
    })
   @DeleteMapping("/supprimer")
    public String supprimerUtilisateur(@Valid @RequestBody Utilisateur utilisateur) {
        return utilisateurService.supprimeUtilisateur(utilisateur);
    }


}
