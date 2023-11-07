package com.odk3.projet_tp_api.Controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.odk3.projet_tp_api.Service.StatusEtudiantService;
import com.odk3.projet_tp_api.model.StatusEtudiant;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
//@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/status")
public class StatusEtudiantController {
    @Autowired
    StatusEtudiantService statusEtudiantService;


    ///////////////////////////////////////////
    @PostMapping("/create")
    @Operation(summary = "Création d'un StatusEtudiant")
    public ResponseEntity<StatusEtudiant> createStatusEtudiant(
            @Valid @RequestParam("statusEtudiant") String statusEtudiantString,
            @RequestParam(value ="bulletin", required=false) MultipartFile multipartFile) throws Exception {

        StatusEtudiant statusEtudiant = new StatusEtudiant();
        try{
            statusEtudiant = new JsonMapper().readValue(statusEtudiantString, StatusEtudiant.class);
        }catch(JsonProcessingException e){
            throw new Exception(e.getMessage());
        }

        StatusEtudiant savedStatusEtudiant = statusEtudiantService.createStatusEtudiant(statusEtudiant,multipartFile);

        return new ResponseEntity<>(savedStatusEtudiant, HttpStatus.CREATED);
    }

    //////////////////////////////////////////
    //////////////////////////

    @PutMapping("/update/{id}")
    @Operation(summary = "Mise à jour d'un StatusEtudiant par ID")
    public ResponseEntity<StatusEtudiant> updateStatusEtudiant(
            @PathVariable int id,
            @Valid @RequestParam("statusEtudiant") String statusEtudiantString,
            @RequestParam(value ="bulletin", required=false) MultipartFile multipartFile) throws Exception {

        StatusEtudiant statusEtudiant = new StatusEtudiant();
        try {
            statusEtudiant = new JsonMapper().readValue(statusEtudiantString, StatusEtudiant.class);
        } catch (JsonProcessingException e) {
            throw new Exception(e.getMessage());
        }

        StatusEtudiant updatedStatusEtudiant = statusEtudiantService.updateStatusEtudiant(id, statusEtudiant, multipartFile);

        return new ResponseEntity<>(updatedStatusEtudiant, HttpStatus.OK);
    }

    ////////////////////////////
}
