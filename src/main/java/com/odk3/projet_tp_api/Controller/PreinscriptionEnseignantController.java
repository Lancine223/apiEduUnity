package com.odk3.projet_tp_api.Controller;

import com.odk3.projet_tp_api.Service.PreinscriptionEnseignantService;
import com.odk3.projet_tp_api.model.PreinscriptionEnseignant;
import com.odk3.projet_tp_api.model.Utilisateur;
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
@RequestMapping("/preinscription")
public class PreinscriptionEnseignantController {
    @Autowired
    PreinscriptionEnseignantService preinscritservice;



    @Operation(summary = "Preinscription enseignant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "enseignant inserer",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Utilisateur.class))
            }),
            @ApiResponse(responseCode = "400",description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "409",description = "enseignat exist déjà", content = @Content),
            @ApiResponse(responseCode = "500",description = "Erreur server", content = @Content)
    })
    @PostMapping("/add")
    public Object PreinscritEnseignant(@Valid @RequestBody PreinscriptionEnseignant preinscriptionEnseignant){
        return preinscritservice.creerPreinscriptionEnseignant(preinscriptionEnseignant);
    }





    @Operation(summary = "Renvoie la liste des preinscrits")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "List renvoyer",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Utilisateur.class))
            }),
            @ApiResponse(responseCode = "400",description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "204",description = "List vide", content = @Content),
            @ApiResponse(responseCode = "500",description = "Erreur server", content = @Content)
    })
    @GetMapping("/list")
    public List<PreinscriptionEnseignant> allpreinscrits(){
        return preinscritservice.listPreinscriptionEnseignants();
    }

    @Operation(summary = "Supprimer un preinscrit")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Preinscription supprimer",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Utilisateur.class))
            }),
            @ApiResponse(responseCode = "400",description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "404",description = "Preinscrit introuvable", content = @Content),
            @ApiResponse(responseCode = "500",description = "Erreur server", content = @Content)
    })
    @DeleteMapping("/supprimer")
    public String supprimerPreinscrit(@Valid @RequestBody PreinscriptionEnseignant preinscriptionEnseignant) {
        return preinscritservice.supprimePreinscriptionEnseignant(preinscriptionEnseignant);
    }
}
