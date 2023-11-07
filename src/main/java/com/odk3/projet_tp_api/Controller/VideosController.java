package com.odk3.projet_tp_api.Controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.odk3.projet_tp_api.model.Utilisateur;
import io.swagger.v3.oas.annotations.Parameter;
import com.odk3.projet_tp_api.Service.VideosService;
import com.odk3.projet_tp_api.model.Videos;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
//@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/video")
public class VideosController {

    @Autowired
    VideosService videosService;
    @GetMapping("/listvideos/{idEnseignant}")
    @Operation(summary = "Affichage la liste  des videos à travers l'id de l'enseignant")
    public ResponseEntity<List<Videos>> listeVideosByIdEnseignant(@PathVariable int idEnseignant){
        return  new ResponseEntity<>(videosService.allVideosByEnseignant(idEnseignant), HttpStatus.OK);
    }


    ///////////////////////////////////////////
    @PostMapping("/create")
    @Operation(summary = "Création d'une video")
    public ResponseEntity<Videos> createVideo(
            @Valid @RequestParam("videos") String utilisateurString,
            @RequestParam(value ="video", required=false) MultipartFile multipartFile) throws Exception {

        Videos videos = new Videos();
        try{
            videos = new JsonMapper().readValue(utilisateurString, Videos.class);
        }catch(JsonProcessingException e){
            throw new Exception(e.getMessage());
        }

        Videos savedVideos = videosService.createVideos(videos ,multipartFile);

        return new ResponseEntity<>(savedVideos, HttpStatus.CREATED);
    }

    //////////////////////////////////////////
    //////////////////////////
    @Operation(summary = "Supprimer une video ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Video supprimer",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Videos.class))
            }),
            @ApiResponse(responseCode = "400",description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "404",description = "Video introuvable", content = @Content),
            @ApiResponse(responseCode = "500",description = "Erreur server", content = @Content)
    })
    @DeleteMapping("/supprimer")
    public String supprimerVideos(@Valid @RequestBody Videos videos) {
        return videosService.supprimeVideos(videos);
    }
    ////////////////////////////

}
