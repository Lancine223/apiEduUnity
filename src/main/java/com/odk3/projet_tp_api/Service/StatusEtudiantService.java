package com.odk3.projet_tp_api.Service;

import com.odk3.projet_tp_api.Repository.StatusEtudiantRepository;
import com.odk3.projet_tp_api.exception.DuplicateException;
import com.odk3.projet_tp_api.exception.NotFoundException;
import com.odk3.projet_tp_api.model.StatusEtudiant;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class StatusEtudiantService {
    @Autowired
    StatusEtudiantRepository statusEtudiantRepository;

    public StatusEtudiant createStatusEtudiant(StatusEtudiant statusEtudiant, MultipartFile multipartFile) throws Exception{
        if (statusEtudiantRepository.findByIdStatusEtudiant(statusEtudiant.getIdStatusEtudiant()) == null) {

            if(multipartFile != null){
                String location = "C:\\xampp\\htdocs\\eduunity";
                try{
                    Path rootlocation = Paths.get(location);
                    if(!Files.exists(rootlocation)){
                        Files.createDirectories(rootlocation);
                        Files.copy(multipartFile.getInputStream(),rootlocation.resolve(multipartFile.getOriginalFilename()));
                        statusEtudiant.setBulletin("http://localhost/eduunity/"+multipartFile.getOriginalFilename());
                    }else{
                        try{
                            String nom = location+"\\"+multipartFile.getOriginalFilename();
                            Path name = Paths.get(nom);
                            if(!Files.exists(name)){
                                Files.copy(multipartFile.getInputStream(),rootlocation.resolve(multipartFile.getOriginalFilename()));
                                statusEtudiant.setBulletin("http://localhost/eduunity/"+multipartFile.getOriginalFilename());
                            }else{
                                Files.delete(name);
                                Files.copy(multipartFile.getInputStream(),rootlocation.resolve(multipartFile.getOriginalFilename()));
                                statusEtudiant.setBulletin("http://localhost/eduunity/"+multipartFile.getOriginalFilename());
                            }
                        }catch(Exception e){
                            throw new Exception("some error");
                        }
                    }
                }catch(Exception e){
                    throw new Exception(e.getMessage());
                }
            }

            return statusEtudiantRepository.save(statusEtudiant);
        } else {
            throw new DuplicateException("Cette id existe déjà");}}
//La fin du methode ajouter

    public StatusEtudiant updateStatusEtudiant(int id, StatusEtudiant statusEtudiant, MultipartFile multipartFile) throws Exception {
        StatusEtudiant existingStatusEtudiant = statusEtudiantRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("StatusEtudiant introuvable avec l'ID : " + id));

        // Appliquez les modifications souhaitées à l'utilisateur existant.
        existingStatusEtudiant.setExamen(statusEtudiant.getExamen());
        existingStatusEtudiant.setEtudiant(statusEtudiant.getEtudiant());
        if(multipartFile != null){
            String location = "C:\\xampp\\htdocs\\eduunity";
            try{
                Path rootlocation = Paths.get(location);
                if(!Files.exists(rootlocation)){
                    Files.createDirectories(rootlocation);
                    Files.copy(multipartFile.getInputStream(),rootlocation.resolve(multipartFile.getOriginalFilename()));
                    existingStatusEtudiant.setBulletin("http://localhost/eduunity/"+multipartFile.getOriginalFilename());
                }else{
                    try{
                        String nom = location+"\\"+multipartFile.getOriginalFilename();
                        Path name = Paths.get(nom);
                        if(!Files.exists(name)){
                            Files.copy(multipartFile.getInputStream(),rootlocation.resolve(multipartFile.getOriginalFilename()));
                            existingStatusEtudiant.setBulletin("http://localhost/eduunity/"+multipartFile.getOriginalFilename());
                        }else{
                            Files.delete(name);
                            Files.copy(multipartFile.getInputStream(),rootlocation.resolve(multipartFile.getOriginalFilename()));
                            existingStatusEtudiant.setBulletin("http://localhost/eduunity/"+multipartFile.getOriginalFilename());
                        }
                    }catch(Exception e){
                        throw new Exception("some error");
                    }
                }
            }catch(Exception e){
                throw new Exception(e.getMessage());
            }
        }

        return statusEtudiantRepository.save(existingStatusEtudiant);
    }

    public StatusEtudiant getParIdEtudiant(int idEtudiant){

        // Obtention de tous les budget dans la base de donnÃ©es
        StatusEtudiant statusEtudiant = statusEtudiantRepository.findByEtudiantIdEtudiant(idEtudiant);

        // Si la liste est vide, le systÃ¨me lÃ¨vera une exception
        if (statusEtudiant == null)
            throw new EntityNotFoundException("Billetin non trouver !");
        // Dans le cas contraire le systÃ¨me retourne la liste
        return statusEtudiant;
    }

}
