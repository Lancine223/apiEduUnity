package com.odk3.projet_tp_api.Controller;

import com.odk3.projet_tp_api.Service.ForumService;
import com.odk3.projet_tp_api.model.Forum;
import com.odk3.projet_tp_api.model.Niveau;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

}
