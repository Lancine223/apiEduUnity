package com.odk3.projet_tp_api.Service;

import com.odk3.projet_tp_api.Repository.VideosRepository;
import com.odk3.projet_tp_api.exception.NotFoundException;
import com.odk3.projet_tp_api.model.Videos;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class VideosService {

@Autowired
    VideosRepository videosRepository;

    //Le debut du methode ajouter
    public Videos createVideos(Videos videos, MultipartFile multipartFile) throws Exception{
        if (videosRepository.findByTitreContains(videos.getTitre()) == null) {

            if(multipartFile != null){
                String location = "C:\\xampp\\htdocs\\eduunity";
                try{
                    Path rootlocation = Paths.get(location);
                    if(!Files.exists(rootlocation)){
                        Files.createDirectories(rootlocation);
                        Files.copy(multipartFile.getInputStream(),rootlocation.resolve(multipartFile.getOriginalFilename()));
                        videos.setVideo("http://localhost/eduunity/"+multipartFile.getOriginalFilename());
                    }else{
                        try{
                            String nom = location+"\\"+multipartFile.getOriginalFilename();
                            Path name = Paths.get(nom);
                            if(!Files.exists(name)){
                                Files.copy(multipartFile.getInputStream(),rootlocation.resolve(multipartFile.getOriginalFilename()));
                                videos.setVideo("http://localhost/eduunity/"+multipartFile.getOriginalFilename());
                            }else{
                                Files.delete(name);
                                Files.copy(multipartFile.getInputStream(),rootlocation.resolve(multipartFile.getOriginalFilename()));
                                videos.setVideo("http://localhost/eduunity/"+multipartFile.getOriginalFilename());
                            }
                        }catch(Exception e){
                            throw new Exception("some error");
                        }
                    }
                }catch(Exception e){
                    throw new Exception(e.getMessage());
                }
            }

            return videosRepository.save(videos);
        } else {
            throw new EntityExistsException("Cet titre existe déjà");}}
//La fin du methode ajouter
    public List<Videos> allVideosByEnseignant(int idEnseignant){

        // Obtention de tous les budget dans la base de donnÃ©es
        List<Videos> VideosList = videosRepository.findByEnseignantIdEnseignant(idEnseignant);

        // Si la liste est vide, le systÃ¨me lÃ¨vera une exception
        if (VideosList.isEmpty())
            throw new EntityNotFoundException("Aucune video trouvé");

        // Dans le cas contraire le systÃ¨me retourne la liste
        return VideosList;
    }
    /////////////suppression
    public String supprimeVideos(Videos videos) {

        if (videosRepository.findByIdVideos(videos.getIdVideos()) != null) {
            videosRepository.delete(videos);
            return "Succès";
        } else {
            throw  new NotFoundException("Cette video n'existe pas");
        }

    }
    ////////
}
