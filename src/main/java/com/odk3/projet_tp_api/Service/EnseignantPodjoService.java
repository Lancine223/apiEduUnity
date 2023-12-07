package com.odk3.projet_tp_api.Service;

import com.odk3.projet_tp_api.Repository.ClasseRepository;
import com.odk3.projet_tp_api.Repository.EnseignantRepository;
import com.odk3.projet_tp_api.Repository.FiliereRepository;
import com.odk3.projet_tp_api.Repository.NiveauRepository;
import com.odk3.projet_tp_api.exception.DuplicateException;
import com.odk3.projet_tp_api.exception.NotFoundException;
import com.odk3.projet_tp_api.model.EmailDetails;
import com.odk3.projet_tp_api.model.Enseignant;
import com.odk3.projet_tp_api.model.EnseignantPodjo;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Data
public class EnseignantPodjoService {
    @Autowired
    private EnseignantService enseignantService;
    @Autowired
    private EnseignantRepository enseignantRepository;
    @Autowired
    private NiveauRepository niveauRepository;
    @Autowired
    private ClasseRepository classeRepository;
    @Autowired
    FileService fileService;
    @Autowired
    private FiliereRepository filiereRepository;
    @Autowired
    private EmailServiceImpl emailServiceIplm;
    public EnseignantPodjoService(EnseignantService enseignantService,
                             EnseignantRepository enseignantRepository,
                             NiveauRepository niveauRepository,
                             ClasseRepository classeRepository,
                             FiliereRepository filiereRepository, FileService fileService){
        this.enseignantService=enseignantService;
        this.fileService = fileService;
        this.classeRepository = classeRepository;
        this.filiereRepository = filiereRepository;
        this.niveauRepository = niveauRepository;
        this.enseignantRepository = enseignantRepository;
    }
    public String createEnseignantPodjo(EnseignantPodjo enseignantpo, MultipartFile multipartFile) throws Exception {
        //System.out.println(enseignantpo.getEmail());
        if (enseignantRepository.findByEmail(enseignantpo.getEmail()) == null) {

          /*  if(multipartFile != null){
                String location = "C:\\xampp\\htdocs\\eduunity";
                try{
                    Path rootlocation = Paths.get(location);
                    if(!Files.exists(rootlocation)){
                        Files.createDirectories(rootlocation);
                        Files.copy(multipartFile.getInputStream(),rootlocation.resolve(multipartFile.getOriginalFilename()));
                        enseignantpo.setDiplome("http://localhost/eduunity/"+multipartFile.getOriginalFilename());
                    }else{
                        try{
                            String nom = location+"\\"+multipartFile.getOriginalFilename();
                            Path name = Paths.get(nom);
                            if(!Files.exists(name)){
                                Files.copy(multipartFile.getInputStream(),rootlocation.resolve(multipartFile.getOriginalFilename()));
                                enseignantpo.setDiplome("http://localhost/eduunity/"+multipartFile.getOriginalFilename());
                            }else{
                                Files.delete(name);
                                Files.copy(multipartFile.getInputStream(),rootlocation.resolve(multipartFile.getOriginalFilename()));
                                enseignantpo.setDiplome("http://localhost/eduunity/"+multipartFile.getOriginalFilename());
                            }
                        }catch(Exception e){
                            throw new Exception("some error");
                        }
                    }
                }catch(Exception e){
                    throw new Exception(e.getMessage());
                }
            } */

            /*String message = "Votre inscription est pris en compte, merci nous allons vous contacter très bientôt";
            //Alert
            EmailDetails details = new EmailDetails(enseignant.getEmail(), message, "Message de la part EduUnity");
            emailServiceIplm.sendSimpleMail(details);*/
            if (multipartFile != null) {
                ResponseEntity<String> entity = fileService.upload(multipartFile, enseignantpo.getEmail().toString());
                String urlDiplome = entity.getBody();

                Enseignant enseignant = new Enseignant();
                enseignant.setNom(enseignantpo.getNom());
                enseignant.setPrenom(enseignantpo.getPrenom());
                enseignant.setEtablissement(enseignantpo.getEtablissement());
                enseignant.setEmail(enseignantpo.getEmail());
                enseignant.setTelephone(enseignantpo.getTelephone());
                enseignant.setNombreAbonnes(0);
                enseignant.setDiplome(urlDiplome);
                enseignant.setMotDePasse(enseignantpo.getMotDePasse());
                enseignant.setAcces(false);
                enseignant.setApropos(enseignantpo.getApropos());
                enseignant.setNiveau(niveauRepository.findByIdNiveau(enseignantpo.getNiveau()));
                enseignant.setFiliere(filiereRepository.findByIdFiliere(enseignantpo.getFiliere()));
                enseignant.setClasse(classeRepository.findByIdClasse(enseignantpo.getClasse()));
                enseignantRepository.save(enseignant);

                String message = "Votre inscription est pris en compte, merci de patienter, nous allons vous contacter très bientôt";

                //Alert
                EmailDetails details = new EmailDetails(enseignantpo.getEmail(), message, "Message de la part EduUnity");
                emailServiceIplm.sendSimpleMail(details);

                return "Enseignant creer avec succès";
            }
            else {
                throw new NotFoundException("Aucune photo pour le diplôme");
            }

        } else {
            throw new DuplicateException("Cet email existe déjà");
        }
    }
//La fin du methode ajouter

}
