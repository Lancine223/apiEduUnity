package com.odk3.projet_tp_api.Controller;

import com.odk3.projet_tp_api.Service.DomaineService;
import com.odk3.projet_tp_api.model.Domaine;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/domaine")
public class DomaineController {
    @Autowired
    DomaineService domaineService;


    @Operation(summary = "Inserer un domaine")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Domaine inserer",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Domaine.class))
            }),
            @ApiResponse(responseCode = "400",description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "409",description = "Domaine exist déjà", content = @Content),
            @ApiResponse(responseCode = "500",description = "Erreur server", content = @Content)
    })
    @PostMapping("/add")
    public Object ajouterDomaine(@Valid @RequestBody Domaine domaine){
        return domaineService.creerDomaine(domaine);
    }


    @Operation(summary = "Renvoie la liste des domaines")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "List renvoyer",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Domaine.class))
            }),
            @ApiResponse(responseCode = "400",description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "204",description = "List vide", content = @Content),
            @ApiResponse(responseCode = "500",description = "Erreur server", content = @Content)
    })
    @GetMapping("/listdomaine")
    public List<Domaine> allDomaines(){
        return domaineService.listDomaines();
    }


    @Operation(summary = "Modifier un domaine")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Domaine modifier",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Domaine.class))
            }),
            @ApiResponse(responseCode = "400",description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "404",description = "Domaine n'existe pas", content = @Content),
            @ApiResponse(responseCode = "500",description = "Erreur server", content = @Content)
    })
    @PutMapping("/modifier")
    public  Object modifierDomaine(@Valid @RequestBody Domaine domaine) {
        return domaineService.modifierDomaine(domaine);
    }


    @Operation(summary = "Supprimer un domaine")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Domaine supprimer",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Domaine.class))
            }),
            @ApiResponse(responseCode = "400",description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "404",description = "Domaine introuvable", content = @Content),
            @ApiResponse(responseCode = "500",description = "Erreur server", content = @Content)
    })
    @DeleteMapping("/supprimer")
    public String supprimerDomaine(@Valid @RequestBody Domaine domaine) {
        return domaineService.supprimeDomaine(domaine);
    }
}
