package com.odk3.projet_tp_api.Controller;

import com.odk3.projet_tp_api.Service.NiveauService;
import com.odk3.projet_tp_api.model.Niveau;
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
//@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/niveau")
public class NiveauController {
    @Autowired
    NiveauService niveauService;

    @Operation(summary = "Inserer un niveau")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Niveau inserer",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Niveau.class))
            }),
            @ApiResponse(responseCode = "400",description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "409",description = "Niveau exist déjà", content = @Content),
            @ApiResponse(responseCode = "500",description = "Erreur server", content = @Content)
    })
    @PostMapping("/add")
    public Object ajouterNiveau(@Valid @RequestBody Niveau niveau){
        return niveauService.creerNiveau(niveau);
    }


    @Operation(summary = "Renvoie la liste des niveau")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "List renvoyer",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Niveau.class))
            }),
            @ApiResponse(responseCode = "400",description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "204",description = "List vide", content = @Content),
            @ApiResponse(responseCode = "500",description = "Erreur server", content = @Content)
    })
    @GetMapping("/list")
    public List<Niveau> allNiveaus(){
        return niveauService.listNiveaus();
    }




    @Operation(summary = "Modifier un niveau")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Niveau modifier",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Niveau.class))
            }),
            @ApiResponse(responseCode = "400",description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "404",description = "Niveau n'existe pas", content = @Content),
            @ApiResponse(responseCode = "500",description = "Erreur server", content = @Content)
    })
    @PutMapping("/modifier")
    public  Object modifierNiveau(@Valid @RequestBody Niveau niveau) {
        return niveauService.modifierNiveau(niveau);
    }


    @Operation(summary = "Supprimer un niveau")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Niveau supprimer",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Niveau.class))
            }),
            @ApiResponse(responseCode = "400",description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "404",description = "Niveau introuvable", content = @Content),
            @ApiResponse(responseCode = "500",description = "Erreur server", content = @Content)
    })
    @DeleteMapping("/supprimer")
    public String supprimerNiveau(@Valid @RequestBody Niveau niveau) {
        return niveauService.supprimeNiveau(niveau);
    }

}
