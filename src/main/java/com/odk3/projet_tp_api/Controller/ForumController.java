package com.odk3.projet_tp_api.Controller;

import com.odk3.projet_tp_api.Service.ForumService;
import com.odk3.projet_tp_api.model.Forum;
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
@CrossOrigin(origins = "*")
@RestController
//@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/forum")
public class ForumController {
    @Autowired
    ForumService forumService;

    @GetMapping("/list/{idEnseignant}")
    @Operation(summary = "Affichage la liste  des niveaux à travers l'id de l'domaine")
    public ResponseEntity<List<Forum>> listeByForums(@PathVariable int idEnseignant){
        return  new ResponseEntity<>(forumService.ListForumParEnseignant(idEnseignant), HttpStatus.OK);
    }

    @Operation(summary = "Inserer un forum")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "forum inserer",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Forum.class))
            }),
            @ApiResponse(responseCode = "400",description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "409",description = "forum exist déjà", content = @Content),
            @ApiResponse(responseCode = "500",description = "Erreur server", content = @Content)
    })
    @PostMapping("/add")
    public Object ajouterForum(@Valid @RequestBody Forum forum){
        return forumService.creerForum(forum);
    }


    @GetMapping("/read/{idForum}")
    public ResponseEntity<Forum> getOneForum(@PathVariable int idForum) {
        Forum forum = forumService.getForumBydIdForum(idForum);
        if(forum != null) {
            return ResponseEntity.ok(forum); // Retourner les détails du niveau
        } else {
            return ResponseEntity.notFound().build(); // Gérer le cas où le niveau n'est pas trouvé
        }
    }


    @Operation(summary = "Modifier un forum")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "forum modifier",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Forum.class))
            }),
            @ApiResponse(responseCode = "400",description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "404",description = "forum n'existe pas", content = @Content),
            @ApiResponse(responseCode = "500",description = "Erreur server", content = @Content)
    })
    @PutMapping("/modifier")
    public  Object modifierForum(@Valid @RequestBody Forum forum) {
        return forumService.modifierForum(forum);
    }


    @Operation(summary = "Supprimer un forum")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "forum supprimer",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Forum.class))
            }),
            @ApiResponse(responseCode = "400",description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "404",description = "forum introuvable", content = @Content),
            @ApiResponse(responseCode = "500",description = "Erreur server", content = @Content)
    })
    @DeleteMapping("/supprimer")
    public String supprimerForum(@Valid @RequestBody Forum forum) {
        return forumService.supprimeForum(forum);
    }

}
