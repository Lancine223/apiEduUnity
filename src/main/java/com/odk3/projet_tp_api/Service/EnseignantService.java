package com.odk3.projet_tp_api.Service;

import com.odk3.projet_tp_api.Repository.AproposRepository;
import com.odk3.projet_tp_api.Repository.EnseignantRepository;
import com.odk3.projet_tp_api.exception.DuplicateException;
import com.odk3.projet_tp_api.exception.NoContentException;
import com.odk3.projet_tp_api.exception.NotFoundException;
import com.odk3.projet_tp_api.model.Apropos;
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
    @Autowired
    private AproposRepository aproposRepository;
    public String changeAccess(int id){
        Enseignant enseignant = enseignantRepository.findByIdEnseignant(id);
        boolean estactive = enseignant.getAcces();
        if (enseignant == null)
        {

            throw new NotFoundException("invalid");
        }else {
            enseignant.setAcces(!enseignant.getAcces());
            enseignantRepository.save(enseignant);
            if(estactive == true){
                String message = "Votre compte est désactiver , merci de nous contacter";
                //Alert
                EmailDetails details = new EmailDetails(enseignant.getEmail(), message, "Message de la part EduUnity");
                emailServiceIplm.sendSimpleMail(details);
            }else {
                String message = "Votre compte est activer , merci de vous connecter avec votre compte";
                //Alert
                EmailDetails details = new EmailDetails(enseignant.getEmail(), message, "Message de la part EduUnity");
                emailServiceIplm.sendSimpleMail(details);
            }
        return "succes";
        }
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
            throw new DuplicateException("Cet email existe déjà");
        }
    }
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
        Enseignant EnseignantExistante =enseignantRepository.findByIdEnseignant(enseignant.getIdEnseignant());
        if (EnseignantExistante != null){
            EnseignantExistante.setNom(enseignant.getNom());
            EnseignantExistante.setPrenom(enseignant.getPrenom());
            EnseignantExistante.setEmail(enseignant.getEmail());
            EnseignantExistante.setMotDePasse(enseignant.getMotDePasse());
            EnseignantExistante.setTelephone(enseignant.getTelephone());
            return enseignantRepository.save(EnseignantExistante);
        }
        else {
            throw  new NotFoundException("Cet Enseignant n'existe pas");}
}
    public List<Enseignant> ListEnparClasse(int idClasse){
        if (!enseignantRepository.findByClasseIdClasse(idClasse).isEmpty()){
            return enseignantRepository.findByClasseIdClasse(idClasse);
        }else {
            throw new NotFoundException("Aucun enseignant disponible");
        }
    }




    public Enseignant connectionEnseignant(String email, String motDePasse) {
        Enseignant user = enseignantRepository.findByEmailAndMotDePasse(email, motDePasse);
        if (user == null) {
            throw new NotFoundException("Cet Enseignant n'existe pas");
        }
        if(user.getAcces() != true){
            throw new RuntimeException("Votre compte n'est pas activer");
        }
        return user;
    }
    ////////§/§/////

    public String supprimer(Enseignant enseignant) {
            Apropos apropos = aproposRepository.findByEnseignantIdEnseignant(enseignant.getIdEnseignant());
        if (enseignantRepository.findByIdEnseignant(enseignant.getIdEnseignant()) != null) {

            if(apropos != null){
                aproposRepository.delete(apropos);
            }
            enseignantRepository.delete(enseignant);
            return "Succès";
        } else {
            throw  new NotFoundException("Cet enseignant n'existe pas");
        }

    }


}
