package com.odk3.projet_tp_api.Service;

import com.odk3.projet_tp_api.Repository.AbonnementRepository;
import com.odk3.projet_tp_api.Repository.EnseignantRepository;
import com.odk3.projet_tp_api.Repository.EtudiantRepository;
import com.odk3.projet_tp_api.exception.NoContentException;
import com.odk3.projet_tp_api.exception.NotFoundException;
import com.odk3.projet_tp_api.model.Abonnement;
import com.odk3.projet_tp_api.model.Enseignant;
import com.odk3.projet_tp_api.model.Etudiant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@Service
public class AbonnementService {
    @Autowired
    AbonnementRepository abonnementRepository;
    @Autowired
    EtudiantRepository etudiantRepository;
    @Autowired
    EnseignantRepository enseignantRepository;



    public int countTotalAbonne() {
        return abonnementRepository.countTotalAbonne();
    }

    public List<Abonnement> listAbonnements() {
        if (!abonnementRepository.findAll().isEmpty())
            return abonnementRepository.findAll();
        else
            throw new NoContentException("Aucun Abonnement n'a été trouver");
    }

    public int getTotalMontantAbonnements() {
        // Faites appel à votre couche de données pour récupérer les montants des abonnements
        List<Abonnement> abonnements = abonnementRepository.findAll(); // Récupérez tous les abonnements
        int totalMontant = 0;

        for (Abonnement abonnement : abonnements) {
            totalMontant += abonnement.getMontant();
        }

        return totalMontant;
    }



    public List<Abonnement> ListabonneParEtudiant(int idEtudiant){
        if (!abonnementRepository.findByEtudiantIdEtudiant(idEtudiant).isEmpty()){
            return abonnementRepository.findByEtudiantIdEtudiant(idEtudiant);
        }else {
            throw new NotFoundException("Aucun abonnement disponible");
        }
    }




    public Abonnement BestPratiquePourAbonner(int idEtudiant, Abonnement abonnement) {
        // Récupérer l'étudiant
        Etudiant etudiant = etudiantRepository.findByIdEtudiant(idEtudiant);

        if (etudiant != null) {
            // Vérifier si l'étudiant n'est pas déjà abonn
                // Récupérer l'enseignant associé à l'abonnement
                Enseignant enseignant = enseignantRepository.findByIdEnseignant(abonnement.getEnseignant().getIdEnseignant());


                // Vérifier si la classe de l'enseignant n'est pas null
                if (enseignant != null) {
                    double montantPayableClasse = enseignant.getClasse().getMontant();
                    if(abonnement.getMontant() < montantPayableClasse || abonnement.getMontant() > montantPayableClasse){
                        throw new RuntimeException("le montant doit être egale à : "+montantPayableClasse+ 'F');
                    }
                    if(enseignant.getAcces() != true){
                        throw new RuntimeException("Cet enseignant n'est pas disponible pour le momment");
                    }

                    // Vérifier si l'enseignant n'a pas déjà atteint le nombre maximal d'abonnés
                    if (enseignant.getNombreAbonnes() < 20) {
                        // Vérifier si l'étudiant est déjà abonné à cet enseignant
                        List<Abonnement> abonnementsExistants = abonnementRepository.findByEtudiantAndEnseignant(etudiant, enseignant);
                        for (Abonnement abonnementExistant : abonnementsExistants) {
                            LocalDate ababb = abonnement.getDateAbonnement().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                            LocalDate abdate = abonnementExistant.getDateAbonnement().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                            if (abdate.plusYears(1).isAfter(ababb) ) {
                                throw new RuntimeException("Vous êtes déjà abonné à cet enseignant avec une date d'abonnement dans la limite d'un an.");
                            }
                        }

                        // Logique pour créer l'abonnement
                        Abonnement newAbonnement = abonnementRepository.save(abonnement);

                        // Mettre à jour la propriété estAbonner de l'étudiant
                        etudiant.setEstAbonner(true);

                        // Incrémenter le nombre d'abonnés de l'enseignant associé à l'abonnement
                        enseignant.setNombreAbonnes(enseignant.getNombreAbonnes() + 1);
                        enseignantRepository.save(enseignant);

                        // Sauvegarder les modifications de l'étudiant
                        etudiantRepository.save(etudiant);

                        return newAbonnement;
                    } else {
                        // L'enseignant a atteint le nombre maximal d'abonnés, vous pouvez gérer cette situation selon vos besoins
                        throw new RuntimeException("L'enseignant a atteint le nombre maximal d'abonnés.");
                    }
                } else {
                    throw new RuntimeException("L 'enseignant est null.");
                }
        } else {
            throw new NotFoundException("Etudiant non trouvé.");
        }
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
