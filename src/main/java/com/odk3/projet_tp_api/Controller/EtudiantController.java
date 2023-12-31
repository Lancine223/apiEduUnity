package com.odk3.projet_tp_api.Controller;
import com.odk3.projet_tp_api.Service.EtudiantPodjoService;
import com.odk3.projet_tp_api.Service.EtudiantService;
import com.odk3.projet_tp_api.model.Etudiant;
import com.odk3.projet_tp_api.model.EtudiantPodjo;
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
@CrossOrigin(origins = "*")
@RestController
//@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/etudiant")
public class EtudiantController {
    @Autowired
    EtudiantService etudiantService;
    @Autowired
    EtudiantPodjoService etudiantPodjoService;

    @Operation(summary = "Ajouter un etudiant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "etudiant inserer avec succès",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Etudiant.class))
            }),
            @ApiResponse(responseCode = "400",description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "409",description = "L'Etudiant exist déjà", content = @Content),
            @ApiResponse(responseCode = "500",description = "Erreur server", content = @Content)
    })
    @PostMapping("/add")
    public Object addEtudiant(@Valid @RequestBody Etudiant etudiant){
        return etudiantService.creerEtudiant(etudiant);
    }


    @Operation(summary = "Connexion d'un Etudiant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Etudiant connecter",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Etudiant.class))
            }),
            @ApiResponse(responseCode = "400",description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "404",description = "Etudiant introuvable", content = @Content),
            @ApiResponse(responseCode = "500",description = "Erreur server", content = @Content)
    })
    @PostMapping("/login")
    public Object connexion(@Parameter(description = "Telephone de l'Etudiant") @RequestParam("telephone") String telephone,
                            @Parameter(description = "Mot de passe de l'Etudiant") @RequestParam("motDePasse") String motDePasse) {
        return etudiantService.connectionEtudiant(telephone, motDePasse);
    }

    @PostMapping("/podjocreate")
    @Operation(summary = "Enregistrer un etudiant")
    public String creer(@RequestBody EtudiantPodjo etudiantPodjo1){

        return etudiantPodjoService.createEtudiantPodjo(etudiantPodjo1);
    }

    @Operation(summary = "Renvoie la liste des Etudiants")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "List renvoyer",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Etudiant.class))
            }),
            @ApiResponse(responseCode = "400",description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "204",description = "List vide", content = @Content),
            @ApiResponse(responseCode = "500",description = "Erreur server", content = @Content)
    })
    @GetMapping("/list")
    public List<Etudiant> allEtudiants(){
        return etudiantService.listEtudiants();
    }

    @GetMapping("/etudiants/abonnement/{idEtudiant}")
    public boolean estAbonne(@PathVariable int idEtudiant) {
        return etudiantService.estAbonne(idEtudiant);
    }

    @Operation(summary = "Modifier un Etudiant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Etudiant modifier",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Etudiant.class))
            }),
            @ApiResponse(responseCode = "400",description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "404",description = "Etudiant n'existe pas", content = @Content),
            @ApiResponse(responseCode = "500",description = "Erreur server", content = @Content)
    })
    @PutMapping("/modifier")
    public  Object modifierEtudiant(@Valid @RequestBody Etudiant etudiant) {
        return etudiantService.modifierPodjo(etudiant);
    }
    @Operation(summary = "Supprimer un Etudiant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Etudiant supprimer",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Etudiant.class))
            }),
            @ApiResponse(responseCode = "400",description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "404",description = "Etudiant introuvable", content = @Content),
            @ApiResponse(responseCode = "500",description = "Erreur server", content = @Content)
    })
    @DeleteMapping("/supprimer")
    public String supprimer(@Valid @RequestBody Etudiant etudiant) {
        return etudiantService.supprimeEtudiant(etudiant);
    }
}
