package com.odk3.projet_tp_api.Service;

import com.odk3.projet_tp_api.Repository.EnseignantRepository;
import com.odk3.projet_tp_api.exception.DuplicateException;
import com.odk3.projet_tp_api.exception.NoContentException;
import com.odk3.projet_tp_api.exception.NotFoundException;
import com.odk3.projet_tp_api.model.EmailDetails;
import com.odk3.projet_tp_api.model.Enseignant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class EnseignantService {
    // Pour acceder a la table utilisateur dans la base de donnée
    @Autowired // Injection de dependance
            EnseignantRepository enseignantRepository; // Un variable de type UtilisateurRepository
    @Autowired
    private EmailServiceImpl emailServiceIplm;
    public String changeAccess(int id){
        Enseignant enseignant = enseignantRepository.findByIdEnseignant(id);
        if (enseignant == null)
            throw new NotFoundException("invalid");
            enseignant.setAcces(!enseignant.getAcces());
            enseignantRepository.save(enseignant);
        return "succes";
    }

    public Enseignant createEnseignant(Enseignant enseignant, MultipartFile multipartFile) throws Exception{
        if (enseignantRepository.findByEmail(enseignant.getEmail()) == null) {

            if(multipartFile != null){
                String location = "C:\\xampp\\htdocs\\eduunity";
                try{
                    Path rootlocation = Paths.get(location);
                    if(!Files.exists(rootlocation)){
                        Files.createDirectories(rootlocation);
                        Files.copy(multipartFile.getInputStream(),rootlocation.resolve(multipartFile.getOriginalFilename()));
                        enseignant.setDiplome("http://localhost/eduunity/"+multipartFile.getOriginalFilename());
                    }else{
                        try{
                            String nom = location+"\\"+multipartFile.getOriginalFilename();
                            Path name = Paths.get(nom);
                            if(!Files.exists(name)){
                                Files.copy(multipartFile.getInputStream(),rootlocation.resolve(multipartFile.getOriginalFilename()));
                                enseignant.setDiplome("http://localhost/eduunity/"+multipartFile.getOriginalFilename());
                            }else{
                                Files.delete(name);
                                Files.copy(multipartFile.getInputStream(),rootlocation.resolve(multipartFile.getOriginalFilename()));
                                enseignant.setDiplome("http://localhost/eduunity/"+multipartFile.getOriginalFilename());
                            }
                        }catch(Exception e){
                            throw new Exception("some error");
                        }
                    }
                }catch(Exception e){
                    throw new Exception(e.getMessage());
                }
            }

            String message = "Votre inscription est pris en compte, merci nous allons vous contacter très bientôt";

            //Alert
            EmailDetails details = new EmailDetails(enseignant.getEmail(), message, "Message de la part EduUnity");
            emailServiceIplm.sendSimpleMail(details);

            return enseignantRepository.save(enseignant);
        } else {
            throw new DuplicateException("Cet email existe déjà");}}
//La fin du methode ajouter


    public List<Enseignant> getAllEnseignant(){

        List<Enseignant> enseignants = enseignantRepository.findAll();
        if (enseignants.isEmpty())
        {
            throw new NoContentException("Aucun enseignant trouvé");
        }
        return enseignants;
    }
    public Enseignant getEnseignantById(int idEnseignant){

        Enseignant enseignant= enseignantRepository.findByIdEnseignant(idEnseignant);
        if(enseignant ==null)
            throw new NotFoundException("cet enseignant n'existe pas");
        return enseignant;
    }
    public Enseignant modifierEnseignant(Enseignant enseignant) {

        if (enseignantRepository.findByIdEnseignant(enseignant.getIdEnseignant()) != null){
            return enseignantRepository.save(enseignant);
        }
        else {
            throw  new NotFoundException("Cet Enseignant n'existe pas");
        }

    }
/*
    public Enseignant updateEnseignant(int id, Enseignant enseignant, MultipartFile imageFile) throws Exception {
        try {
            Enseignant EnseignantExistante =enseignantRepository.findByIdEnseignant(enseignant.getIdEnseignant());
            if (EnseignantExistante == null){
                throw new NotFoundException("Désolé  l'Enseignant à modifier n'existe pas");
            }

            // Mettre à jour les champs
            EnseignantExistante.setNom(enseignant.getNom());
            EnseignantExistante.setPrenom(enseignant.getPrenom());
            EnseignantExistante.setEtablissement(enseignant.getEtablissement());
            EnseignantExistante.setEmail(enseignant.getEmail());
            EnseignantExistante.setTelephone(enseignant.getTelephone());
            EnseignantExistante.setMotDePasse(enseignant.getMotDePasse());
            EnseignantExistante.setClasse(enseignant.getClasse());

            // Mettre à jour l'image si fournie
            if (imageFile != null) {
                String emplacementImage = "C:\\xampp\\htdocs\\eduunity";
                String nomImage = UUID.randomUUID().toString() + "_" + imageFile.getOriginalFilename();
                Path cheminImage = Paths.get(emplacementImage).resolve(nomImage);

                Files.copy(imageFile.getInputStream(), cheminImage, StandardCopyOption.REPLACE_EXISTING);
                EnseignantExistante.setDiplome("http://localhost:8080/eduunity/" + nomImage);
            }

            // Mettre à jour l'audio si fourni
            if (audioFile != null) {
                String emplacementAudio = "C:\\xampp\\htdocs\\keneyaDeme\\audios";
                String nomAudio = UUID.randomUUID().toString() + "_" + audioFile.getOriginalFilename();
                Path cheminAudio = Paths.get(emplacementAudio).resolve(nomAudio);

                Files.copy(audioFile.getInputStream(), cheminAudio, StandardCopyOption.REPLACE_EXISTING);
                maladieExistante.setAudio("http://localhost/keneyaDeme/audios/" + nomAudio);
            }

            Enregistrer la maladie mise à jour
           // return enseignantRepository.save(EnseignantExistante);
        } catch (NotFoundException ex) {
            throw new NotFoundException("Une erreur s'est produite lors de la mise à jour de l' enseignant avec l'ID : " + id);

        }
    }
*/

    public String changeAccessE(int id){
        Enseignant enseignant = enseignantRepository.findByIdEnseignant(id);
        if (enseignant == null) {
            throw new NotFoundException("Invalid");
        }

        boolean oldStatus = enseignant.getAcces();
        boolean newStatus = !oldStatus;

        enseignant.setAcces(newStatus);
        enseignantRepository.save(enseignant);

        if (oldStatus != newStatus) {
            String message = (newStatus) ? "Votre compte est activé. Vous pouvez maintenant vous connecter avec votre email et mot de passe." : "Votre compte est désactivé. Vous ne pourrez plus vous connecter. Merci de nous contacter.";
            EmailDetails details = new EmailDetails(enseignant.getEmail(), message, "Message de la part EduUnity");
            emailServiceIplm.sendSimpleMail(details);
        }

        return "success";
    }


    public String supprimer(int idEnseignant) {
        Enseignant enseignant = enseignantRepository.findByIdEnseignant(idEnseignant);
        if (enseignant != null) {
            enseignantRepository.deleteById(idEnseignant);
            return "Enseignant supprimé avec succès.";
        } else {

            return "Enseignant non trouvé.";
        }
    }




    public Enseignant connectionEnseignant(String email, String motDePasse) {
        Enseignant user = enseignantRepository.findByEmailAndMotDePasse(email, motDePasse);
        if (user == null) {
            throw new NotFoundException("Cet Enseignant n'existe pas");
        }

        return user;
    }
    ////////§/§/////




}
