package com.odk3.projet_tp_api.Controller;

import com.odk3.projet_tp_api.Service.AproposService;
import com.odk3.projet_tp_api.model.Apropos;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "*")
@RestController
//@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/apropos")
public class AproposController {
    @Autowired
    AproposService aproposService;

    @Operation(summary = "Inserer un apropos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "apropos inserer",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Apropos.class))
            }),
            @ApiResponse(responseCode = "400",description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "409",description = "apropos exist déjà", content = @Content),
            @ApiResponse(responseCode = "500",description = "Erreur server", content = @Content)
    })
    @PostMapping("/add")
    public Object ajouterApropos(@Valid @RequestBody Apropos apropos){
        return aproposService.creerApropos(apropos);
    }


    @Operation(summary = "Modifier apropos d'un enseignant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "apropos modifier",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Apropos.class))
            }),
            @ApiResponse(responseCode = "400",description = "Mauvaise requete", content = @Content),
            @ApiResponse(responseCode = "404",description = "apropos n'existe pas", content = @Content),
            @ApiResponse(responseCode = "500",description = "Erreur server", content = @Content)
    })
    @PutMapping("/modifier")
    public  Object modifierApropos(@Valid @RequestBody Apropos apropos) {
        return aproposService.modifierApropos(apropos);
    }

}
