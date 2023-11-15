package com.odk3.projet_tp_api.Service;

import com.odk3.projet_tp_api.Repository.CoursRepository;
import com.odk3.projet_tp_api.exception.DuplicateException;
import com.odk3.projet_tp_api.exception.NotFoundException;
import com.odk3.projet_tp_api.model.Cours;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Service
public class CoursService {
    @Autowired
    CoursRepository coursRepository;

    public Cours createCours(Cours cours, MultipartFile multipartFile) throws Exception{
        if (coursRepository.findByIdCours(cours.getIdCours()) == null) {

            if(multipartFile != null){
                String location = "C:\\xampp\\htdocs\\eduunity";
                try{
                    Path rootlocation = Paths.get(location);
                    if(!Files.exists(rootlocation)){
                        Files.createDirectories(rootlocation);
                        Files.copy(multipartFile.getInputStream(),rootlocation.resolve(multipartFile.getOriginalFilename()));
                        cours.setDocument("http://localhost/eduunity/"+multipartFile.getOriginalFilename());
                    }else{
                        try{
                            String nom = location+"\\"+multipartFile.getOriginalFilename();
                            Path name = Paths.get(nom);
                            if(!Files.exists(name)){
                                Files.copy(multipartFile.getInputStream(),rootlocation.resolve(multipartFile.getOriginalFilename()));
                                cours.setDocument("http://localhost/eduunity/"+multipartFile.getOriginalFilename());
                            }else{
                                Files.delete(name);
                                Files.copy(multipartFile.getInputStream(),rootlocation.resolve(multipartFile.getOriginalFilename()));
                                cours.setDocument("http://localhost/eduunity/"+multipartFile.getOriginalFilename());
                            }
                        }catch(Exception e){
                            throw new Exception("some error");
                        }
                    }
                }catch(Exception e){
                    throw new Exception(e.getMessage());
                }
            }

            return coursRepository.save(cours);
        } else {
            throw new DuplicateException("Cet cours existe déjà");}
    }
//La fin du methode ajouter


    public Cours updateCours(int id, Cours cours, MultipartFile docFile) throws Exception {
        try {
            Cours CoursExistante =coursRepository.findByIdCours(cours.getIdCours());
            if (CoursExistante == null){
                throw new NotFoundException("Désolé  Cours à modifier n'existe pas");
            }

            // Mettre à jour les champs
            CoursExistante.setTitre(cours.getTitre());
            CoursExistante.setEnseignant(cours.getEnseignant());

            // Mettre à jour l'image si fournie
            if (docFile != null) {
                String emplacementImage = "C:\\xampp\\htdocs\\eduunity";
                String nomImage = UUID.randomUUID().toString() + "_" + docFile.getOriginalFilename();
                Path cheminImage = Paths.get(emplacementImage).resolve(nomImage);

                Files.copy(docFile.getInputStream(), cheminImage, StandardCopyOption.REPLACE_EXISTING);
                CoursExistante.setDocument("http://localhost/eduunity/" + nomImage);
            }
            // Mettre à jour l'audio si fourni
           // Enregistrer la maladie mise à jour
            return coursRepository.save(CoursExistante);
        } catch (NotFoundException ex) {
            throw new NotFoundException("Une erreur s'est produite lors de la mise à jour de Cours avec l'ID : " + id);
        }

    }



    //++++++++++++++++++++++++
    public List<Cours> ListCoursParEnseignant(int idEnseignant){

        // Obtention de tous les budget dans la base de donnÃ©es
        List<Cours> ListCours = coursRepository.findByEnseignantIdEnseignant(idEnseignant);
        // Si la liste est vide, le systÃ¨me lÃ¨vera une exception
        if (ListCours.isEmpty())
            throw new EntityNotFoundException("Aucun Cours trouvé");
        // Dans le cas contraire le systÃ¨me retourne la liste
        return ListCours;
    }
    //+++++++++
    public String supprimeCours(Cours cours) {

        if (coursRepository.findByIdCours(cours.getIdCours()) != null) {
            coursRepository.delete(cours);
            return "Succès";
        } else {
            throw  new NotFoundException("Cet Cours n'existe pas");
        }

    }
}
