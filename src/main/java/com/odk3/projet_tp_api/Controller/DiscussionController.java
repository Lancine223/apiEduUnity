package com.odk3.projet_tp_api.Controller;

import com.odk3.projet_tp_api.Service.DiscussionPodjoService;
import com.odk3.projet_tp_api.Service.DiscussionService;
import com.odk3.projet_tp_api.model.Discussion;
import com.odk3.projet_tp_api.model.DiscussionPodjo;
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
@RequestMapping("/discussion")
public class DiscussionController {
    @Autowired
    DiscussionService discussionService;

    @Autowired
    DiscussionPodjoService discussionPodjoService;
    @GetMapping("/list/{idForum}")
    @Operation(summary = "Affichage de la liste  des discussion à travers l'id de l'Forum")
    public ResponseEntity<List<Discussion>> listeByDiscussion(@PathVariable int idForum){
        return  new ResponseEntity<>(discussionService.ListDiscussionParForum(idForum), HttpStatus.OK);
    }

    @PostMapping("/podjocreate")
    @Operation(summary = "Envoyer une discussion")
    public String creer(@RequestBody DiscussionPodjo discussionPodjo){

        return discussionPodjoService.createDiscussionPodjo(discussionPodjo);
    }

    @Operation(summary = "Inserer une discussion")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "discussion inserer",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Discussion.class))
            }),
            @ApiResponse(responseCode = "400",description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "409",description = "discussion exist déjà", content = @Content),
            @ApiResponse(responseCode = "500",description = "Erreur server", content = @Content)
    })
    @PostMapping("/add")
    public Object ajouterDiscussion(@Valid @RequestBody Discussion  discussion){
        return discussionService.creerDiscussion(discussion);
    }

    @Operation(summary = "Supprimer un discussion")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "discussion supprimer",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Discussion.class))
            }),
            @ApiResponse(responseCode = "400",description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "404",description = "discussion introuvable", content = @Content),
            @ApiResponse(responseCode = "500",description = "Erreur server", content = @Content)
    })
    @DeleteMapping("/supprimer")
    public String supprimerDiscussion(@Valid @RequestBody Discussion discussion) {
        return discussionService.supprimeDiscussion(discussion);
    }
}
