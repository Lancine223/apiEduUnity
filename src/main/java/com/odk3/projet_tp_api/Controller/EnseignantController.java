package com.odk3.projet_tp_api.Controller;

import com.odk3.projet_tp_api.Service.EnseignantService;
import com.odk3.projet_tp_api.model.Administrateur;
import com.odk3.projet_tp_api.model.Enseignant;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enseignant")
public class EnseignantController {
    @Autowired
    EnseignantService enseignantService;


    @Operation(summary = "Ajouter un enseignant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "enseignant inserer avec succès",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Administrateur.class))
            }),
            @ApiResponse(responseCode = "400",description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "409",description = "L'enseignant exist déjà", content = @Content),
            @ApiResponse(responseCode = "500",description = "Erreur server", content = @Content)
    })
    @PostMapping("/add")
    public Object addEnseignant(@Valid @RequestBody Enseignant enseignant){
        return enseignantService.creerEnseignant(enseignant);
    }


    @Operation(summary = "Connexion d'un enseignant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Enseignant connecter",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Enseignant.class))
            }),
            @ApiResponse(responseCode = "400",description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "404",description = "Enseignant introuvable", content = @Content),
            @ApiResponse(responseCode = "500",description = "Erreur server", content = @Content)
    })
    @PostMapping("/login")
    public Object connexion(@Parameter(description = "Email de l'enseignant") @RequestParam("email") String email,
                            @Parameter(description = "Mot de passe de l'enseignant") @RequestParam("mot_de_passe") String mot_de_passe) {
        return enseignantService.connectionEnseignant(email, mot_de_passe);
    }


    @Operation(summary = "Renvoie la liste des Enseignants")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "List renvoyer",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Administrateur.class))
            }),
            @ApiResponse(responseCode = "400",description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "204",description = "List vide", content = @Content),
            @ApiResponse(responseCode = "500",description = "Erreur server", content = @Content)
    })
    @GetMapping("/list")
    public List<Enseignant> allEnseignants(){
        return enseignantService.listEnseignants();
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


    @Operation(summary = "Supprimer un Enseignant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Enseignant supprimer",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Enseignant.class))
            }),
            @ApiResponse(responseCode = "400",description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "404",description = "Enseignant introuvable", content = @Content),
            @ApiResponse(responseCode = "500",description = "Erreur server", content = @Content)
    })
    @DeleteMapping("/supprimer")
    public String supprimer(@Valid @RequestBody Enseignant enseignant) {
        return enseignantService.supprimeEnseignant(enseignant);
    }

}
