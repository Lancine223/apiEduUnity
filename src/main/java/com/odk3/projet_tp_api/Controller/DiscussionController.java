package com.odk3.projet_tp_api.Controller;

import com.odk3.projet_tp_api.Service.DiscussionService;
import com.odk3.projet_tp_api.model.Discussion;
import com.odk3.projet_tp_api.model.Forum;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/discussion")
public class DiscussionController {
    @Autowired
    DiscussionService discussionService;
    @GetMapping("/list/{idForum}")
    @Operation(summary = "Affichage de la liste  des discussion à travers l'id de l'Forum")
    public ResponseEntity<List<Discussion>> listeByDiscussion(@PathVariable int idForum){
        return  new ResponseEntity<>(discussionService.ListDiscussionParForum(idForum), HttpStatus.OK);
    }
}
