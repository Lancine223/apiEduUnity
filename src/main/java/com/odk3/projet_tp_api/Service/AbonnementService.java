package com.odk3.projet_tp_api.Service;

import com.odk3.projet_tp_api.Repository.AbonnementRepository;
import com.odk3.projet_tp_api.Repository.EtudiantRepository;
import com.odk3.projet_tp_api.exception.NotFoundException;
import com.odk3.projet_tp_api.model.Abonnement;
import com.odk3.projet_tp_api.model.Etudiant;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AbonnementService {
    @Autowired
    AbonnementRepository abonnementRepository;
    EtudiantRepository etudiantRepository;

    public Abonnement creerAbonnement(Abonnement abonnement){
        Etudiant etudiant = etudiantRepository.findByClasse(abonnement.getEtudiant().getClasse());
        int montantpayable = abonnement.getMontant();
        int somme = etudiant.getClasse().getMontant();
        if (montantpayable < somme){
            throw new RuntimeException("votre montant est insuffisant, le montant payable est :"+somme);
        }else {
            return abonnementRepository.save(abonnement);
        }
    }


    public List<Abonnement> ListabonneParEtudiant(int idEtudiant){

        // Obtention de tous les budget dans la base de donnÃ©es
        List<Abonnement> ListAbonner = abonnementRepository.findByEtudiantIdEtudiant(idEtudiant);

        // Si la liste est vide, le systÃ¨me lÃ¨vera une exception
        if (ListAbonner.isEmpty())
            throw new EntityNotFoundException("Aucun abonnement trouvé");

        // Dans le cas contraire le systÃ¨me retourne la liste
        return ListAbonner;
    }

    /////////////suppression
    public String supprimeAbonnement(Abonnement abonnement) {

        if (abonnementRepository.findById(abonnement.getIdAbonnement()) != null) {
            abonnementRepository.delete(abonnement);
            return "Succès";
        } else {
            throw  new NotFoundException("Cet Abonnement n'existe pas");
        }

    }

}
