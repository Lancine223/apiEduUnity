package com.odk3.projet_tp_api.Controller;

import com.odk3.projet_tp_api.Service.ClasseService;
import com.odk3.projet_tp_api.model.Classe;
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
@RequestMapping("/classe")
public class ClasseController {
    @Autowired
    ClasseService classeService;

    @Operation(summary = "Inserer un Classe")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Classe inserer",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Classe.class))
            }),
            @ApiResponse(responseCode = "400",description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "409",description = "Classe exist déjà", content = @Content),
            @ApiResponse(responseCode = "500",description = "Erreur server", content = @Content)
    })
    @PostMapping("/add")
    public Object ajouterClasse(@Valid @RequestBody Classe  classe){

        return classeService.creerClasse(classe);
    }

    @GetMapping("/read/{idClasse}")
    public ResponseEntity<Classe> getOneClasse(@PathVariable int idClasse) {
        Classe classe = classeService.getClasseById(idClasse);
        if(classe != null) {
            return ResponseEntity.ok(classe); // Retourner les détails du niveau
        } else {
            return ResponseEntity.notFound().build(); // Gérer le cas où le niveau n'est pas trouvé
        }
    }

    @Operation(summary = "Renvoie la liste des Classes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "List renvoyer",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Classe.class))
            }),
            @ApiResponse(responseCode = "400",description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "204",description = "List vide", content = @Content),
            @ApiResponse(responseCode = "500",description = "Erreur server", content = @Content)
    })
    @GetMapping("/list")
    public List<Classe> allClasses(){
        return classeService.listClasses();
    }

    @GetMapping("/list/{idFiliere}")
    @Operation(summary = "Affichage la liste  des Classes à travers l'id filiere")
    public ResponseEntity<List<Classe>> listeByFiliere(@PathVariable int idFiliere){
        return  new ResponseEntity<>(classeService.ListClasseParFiliere(idFiliere), HttpStatus.OK);
    }


    @Operation(summary = "Modifier une Classe")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Classe modifier",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Classe.class))
            }),
            @ApiResponse(responseCode = "400",description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "404",description = "Classe n'existe pas", content = @Content),
            @ApiResponse(responseCode = "500",description = "Erreur server", content = @Content)
    })
    @PutMapping("/modifier")
    public  Object modifierClasse(@Valid @RequestBody Classe classe) {
        return classeService.modifierClasse(classe);
    }


    @Operation(summary = "Supprimer une Classe")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Classe supprimer",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Classe.class))
            }),
            @ApiResponse(responseCode = "400",description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "404",description = "Classe introuvable", content = @Content),
            @ApiResponse(responseCode = "500",description = "Erreur server", content = @Content)
    })
    @DeleteMapping("/supprimer")
    public String supprimerClasse(@Valid @RequestBody Classe classe) {
        return classeService.supprimeClasse(classe);
    }
}
