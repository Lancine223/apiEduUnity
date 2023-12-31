package com.odk3.projet_tp_api.Service;

import com.odk3.projet_tp_api.Repository.AdministrateurRepository;
import com.odk3.projet_tp_api.exception.DuplicateException;
import com.odk3.projet_tp_api.exception.NoContentException;
import com.odk3.projet_tp_api.exception.NotFoundException;
import com.odk3.projet_tp_api.model.Administrateur;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministrateurService {
    // Pour acceder a la table administrateur dans la base de donnée
    @Autowired // Injection de dependance
            AdministrateurRepository administrateurRepository; // Un variable de type UtilisateurRepository

    // Portee , type de retour , nom de la fonction
    public Administrateur creerAdministrateur(Administrateur administrateur){
        if (administrateurRepository.findByEmail(administrateur.getEmail()) == null) {
            return administrateurRepository.save(administrateur);
        } else {
            throw new DuplicateException("Cet email existe déjà");
        }

    }

    public List<Administrateur> listAdministrateurs() {
        if (!administrateurRepository.findAll().isEmpty())
            return administrateurRepository.findAll();
        else
            throw new NoContentException("Aucun administrateur n'a été trouver");
    }

    public Administrateur modifierAdministrateur(Administrateur administrateur) {

        if (administrateurRepository.findByIdAdministrateur(administrateur.getIdAdministrateur()) != null){
            return administrateurRepository.save(administrateur);
        }
        else {
            throw  new NotFoundException("Cet administrateur n'existe pas");
        }

    }


    public String supprimeAdministrateur(Administrateur administrateur) {

        if (administrateurRepository.findByIdAdministrateur(administrateur.getIdAdministrateur()) != null) {
                administrateurRepository.delete(administrateur);
            return "Succès";
        } else {
            throw  new NotFoundException("Cet administrateur n'existe pas");
        }

    }
    public Administrateur getAdministrateurById(int idAdministrateur){

        Administrateur administrateur= administrateurRepository.findByIdAdministrateur(idAdministrateur);
        if(administrateur ==null)
            throw new NotFoundException("cet Administrateur n'existe pas");
        return administrateur;
    }

    public Administrateur connectionAdministrateur(String email, String mon_de_passe) {
        if (administrateurRepository.findByEmailAndMotDePasse(email, mon_de_passe) != null) {
            return (Administrateur) administrateurRepository.findByEmailAndMotDePasse(email, mon_de_passe);
        }else {
            throw  new NotFoundException("Cet administrateur n'existe pas");
        }

    }

    @PostConstruct
    public void init(){
        Administrateur administrateur = new Administrateur();
        administrateur.setEmail("lacinekeita20@gmail.com");
        administrateur.setPrenom("Lanciné");
        administrateur.setMotDePasse("Kambacamara");
        administrateur.setRole("superadmin");
        administrateur.setNom("Keita");
        Administrateur adminExit = administrateurRepository.findByEmail(administrateur.getEmail());
        if (adminExit == null ){
            administrateurRepository.save(administrateur);
        }
    }


}
