package com.odk3.projet_tp_api.Controller;

import com.odk3.projet_tp_api.Service.AdministrateurService;
import com.odk3.projet_tp_api.model.Administrateur;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(originPatterns = "*")

//@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/admin")
public class AdministrateurController {
    @Autowired
    AdministrateurService administrateurService;

    @Operation(summary = "Ajouter un administrateur")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Admin inserer avec succès",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Administrateur.class))
            }),
            @ApiResponse(responseCode = "400",description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "409",description = "L'admin exist déjà", content = @Content),
            @ApiResponse(responseCode = "500",description = "Erreur server", content = @Content)
    })
    @PostMapping("/add")
    public Object addAdministrateur(@Valid @RequestBody Administrateur administrateur){
        return administrateurService.creerAdministrateur(administrateur);
    }


    @Operation(summary = "Connexion d'un administrateur")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Administrateur connecter",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Administrateur.class))
            }),
            @ApiResponse(responseCode = "400",description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "404",description = "Administrateur introuvable", content = @Content),
            @ApiResponse(responseCode = "500",description = "Erreur server", content = @Content)
    })
    @PostMapping("/login")
    public Object connexion(@Parameter(description = "Email de l'administrateur") @RequestParam("email") String email,
                            @Parameter(description = "Mot de passe de l'administrateur") @RequestParam("mot_de_passe") String mot_de_passe) {
        return administrateurService.connectionAdministrateur(email, mot_de_passe);
    }


    @Operation(summary = "Renvoie la liste des administrateur")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "List renvoyer",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Administrateur.class))
            }),
            @ApiResponse(responseCode = "400",description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "204",description = "List vide", content = @Content),
            @ApiResponse(responseCode = "500",description = "Erreur server", content = @Content)
    })
    @GetMapping("/list")
    public List<Administrateur> allAdmins(){
        return administrateurService.listAdministrateurs();
    }


    @Operation(summary = "Modifier un admin")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Administrateur modifier",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Administrateur.class))
            }),
            @ApiResponse(responseCode = "400",description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "404",description = "Administrateur n'existe pas", content = @Content),
            @ApiResponse(responseCode = "500",description = "Erreur server", content = @Content)
    })
    @PutMapping("/modifier")
    public  Object modifierAdministrateur(@Valid @RequestBody Administrateur administrateur) {
        return administrateurService.modifierAdministrateur(administrateur);
    }

    @GetMapping("/read/{id}")
    @Operation(summary = "Affichage  d'un Administrateur")
    public ResponseEntity<Administrateur> getAdministrateurById(@Valid @PathVariable int idAdministrateur){
        return new ResponseEntity<>(administrateurService.getAdministrateurById(idAdministrateur), HttpStatus.OK) ;}
    @GetMapping("/profile")
    public ResponseEntity<Administrateur> getAdminProfile( Administrateur administrateur) {
        // Récupérez les informations du profil de l'administrateur actuellement connecté
        Administrateur admin = administrateurService.connectionAdministrateur(administrateur.getEmail(), administrateur.getMotDePasse());

        if (admin != null) {
            return ResponseEntity.ok(admin);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @Operation(summary = "Supprimer un admin")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Administrateur supprimer",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Administrateur.class))
            }),
            @ApiResponse(responseCode = "400",description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "404",description = "Administrateur introuvable", content = @Content),
            @ApiResponse(responseCode = "500",description = "Erreur server", content = @Content)
    })
    @DeleteMapping("/supprimer")
    public String supprimer(@Valid @RequestBody Administrateur administrateur) {
        return administrateurService.supprimeAdministrateur(administrateur);
    }

}
