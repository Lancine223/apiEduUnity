package com.odk3.projet_tp_api.Controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.odk3.projet_tp_api.Service.CoursService;
import com.odk3.projet_tp_api.model.Cours;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@RestController
@CrossOrigin( origins = "*")
@AllArgsConstructor
@RequestMapping("/cours")
public class CoursController {
    @Autowired
    CoursService coursService;

    @PostMapping("/create")
    @Operation(summary = "Création d'un Cours")
    public ResponseEntity<Cours> createCours(
            @Valid @RequestParam("cours") String coursString,
            @RequestParam(value ="document", required=false) MultipartFile multipartFile) throws Exception {

        Cours cours = new Cours();
        try{
            cours = new JsonMapper().readValue(coursString, Cours.class);
        }catch(JsonProcessingException e){
            throw new Exception(e.getMessage());
        }
        Cours savedCours = coursService.createCours(cours,multipartFile);
        return new ResponseEntity<>(savedCours, HttpStatus.CREATED);
    }

    //////////////////////////////////////////
    @PutMapping("/modifier/{id}")
    @Operation(summary = "Mise à jour d'une Cours par son Id ")
    public ResponseEntity<Cours> updateCours(
            @PathVariable int id,
            @Valid @RequestParam("cours") String coursString,
            @RequestParam(value = "document", required = false) MultipartFile imageFile ){
        Cours coursMiseAJour = new Cours();
        try {
            coursMiseAJour = new JsonMapper().readValue(coursString, Cours.class);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            Cours coursMiseAJournee = coursService.updateCours(id, coursMiseAJour, imageFile);
            return new ResponseEntity<>(coursMiseAJournee, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //////////////////////////

    @GetMapping("/list/{idEnseigant}")
    @Operation(summary = "Affichage la liste  des Cours à travers l'id enseignant")
    public ResponseEntity<List<Cours>> listeByEnseignant(@PathVariable int idEnseigant){
        return  new ResponseEntity<>(coursService.ListCoursParEnseignant(idEnseigant), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "Suppression d'un Cours")
    public ResponseEntity<String> deleteCoursById(@Valid @RequestBody Cours cours){
        return new ResponseEntity<>(coursService.supprimeCours(cours),HttpStatus.OK);}

}
