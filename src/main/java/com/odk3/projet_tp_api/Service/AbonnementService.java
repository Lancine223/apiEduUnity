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
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AbonnementService {
    @Autowired
    AbonnementRepository abonnementRepository;
    @Autowired
    EtudiantRepository etudiantRepository;
    @Autowired
    EnseignantRepository enseignantRepository;

    public Abonnement creerAbonnement(Abonnement abonnement) {
        Etudiant etudiant = etudiantRepository.findByClasse(abonnement.getEtudiant().getClasse());

            int montantpayable = abonnement.getMontant();
            int somme = etudiant.getClasse().getMontant();
            if (montantpayable < somme){
                throw new RuntimeException("votre montant est insuffisant, le montant payable est :"+somme);
            }else if(montantpayable > somme) {
                throw new RuntimeException("votre montant est superieur au montant payable est ");
            }
        return abonnementRepository.save(abonnement);
    }

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


/*
    public List<Abonnement> ListabonneParEtudiant(int idEtudiant){

        // Obtention de tous les budget dans la base de donnÃ©es
        List<Abonnement> ListAbonner = abonnementRepository.findByEtudiantIdEtudiant(idEtudiant);

        // Si la liste est vide, le systÃ¨me lÃ¨vera une exception
        if (ListAbonner.isEmpty())
            throw new EntityNotFoundException("Aucun abonnement trouvé");
        List<Enseignant> ListEnseignant = ListAbonner.forEach(abonnement -> {

        });
        // Dans le cas contraire le systÃ¨me retourne la liste
        return ListAbonner;
    }
*/
    public List<Abonnement> ListabonneParEtudiant(int idEtudiant){
        if (!abonnementRepository.findByEtudiantIdEtudiant(idEtudiant).isEmpty()){
            return abonnementRepository.findByEtudiantIdEtudiant(idEtudiant);
        }else {
            throw new NotFoundException("Aucun abonnement disponible");
        }
    }

    public Abonnement createAbonnementForEtudiant(int idEtudiant, Abonnement abonnement) {
        // Logique pour créer l'abonnement
        Abonnement newAbonnement = abonnementRepository.save(abonnement);

        // Récupérer l'étudiant
        Optional<Etudiant> optionalEtudiant = etudiantRepository.findById(idEtudiant);

        if (optionalEtudiant.isPresent()) {
            Etudiant etudiant = optionalEtudiant.get();
            // Mettre à jour la propriété estAbonner de l'étudiant
            etudiant.setEstAbonner(true);

            // Sauvegarder les modifications de l'étudiant
            etudiantRepository.save(etudiant);
        }

        return newAbonnement;
    }

    //// Bonne pratique de l'abonnement
    /* public Abonnement BestPratiquePourAbonner(int idEtudiant, Abonnement abonnement) {
        // Récupérer l'étudiant
        Etudiant etudiant = etudiantRepository.findByIdEtudiant(idEtudiant);

        if (etudiant != null) {
            // Vérifier si l'étudiant n'est pas déjà abonné
            if (!etudiant.isEstAbonner()) {
                // Récupérer l'enseignant associé à l'abonnement
                Enseignant enseignant = abonnement.getEnseignant();
                System.out.println("Classe de l'enseignant : " + enseignant.getClasse());
                System.out.println("Enseignant : " + enseignant);


                // Vérifier si la classe de l'enseignant n'est pas null
                if (enseignant != null && enseignant.getClasse() != null) {
                    double montantPayableClasse = enseignant.getClasse().getMontant();

                    // Vérifier si l'enseignant n'a pas déjà atteint le nombre maximal d'abonnés
                    if (enseignant.getNombreAbonnes() < 20) {
                        if (Double.compare(abonnement.getMontant(), montantPayableClasse) == 0) {
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
                            throw new IllegalArgumentException("Le montant de l'abonnement doit être strictement égal au montant payable pour la classe");
                        }
                    } else {
                        // L'enseignant a atteint le nombre maximal d'abonnés, vous pouvez gérer cette situation selon vos besoins
                        throw new RuntimeException("L'enseignant a atteint le nombre maximal d'abonnés.");
                    }
                } else {
                    throw new RuntimeException("La classe de l'enseignant est null.");
                }
            } else {
                // L'étudiant est déjà abonné, vous pouvez gérer cette situation selon vos besoins
                throw new RuntimeException("L'étudiant est déjà abonné.");
            }
        } else {
            throw new NotFoundException("Etudiant non trouvé.");
        }
    }
*/


    //////////+++++++++++
    public Abonnement BestPratiquePourAbonner(int idEtudiant, Abonnement abonnement) {
        // Récupérer l'étudiant
        Etudiant etudiant = etudiantRepository.findByIdEtudiant(idEtudiant);

        if (etudiant != null) {
            // Vérifier si l'étudiant n'est pas déjà abonné
            if (!etudiant.isEstAbonner()) {
                // Récupérer l'enseignant associé à l'abonnement
                Enseignant enseignant = abonnement.getEnseignant();

                // Vérifier si la classe de l'enseignant n'est pas null
                if (enseignant != null && enseignant.getClasse() != null) {
                    double montantPayableClasse = enseignant.getClasse().getMontant();

                    // Vérifier si l'enseignant n'a pas déjà atteint le nombre maximal d'abonnés
                    if (enseignant.getNombreAbonnes() < 20) {
                        // Vérifier si l'étudiant est déjà abonné à cet enseignant
                        List<Abonnement> abonnementsExistants = abonnementRepository.findByEtudiantAndEnseignant(etudiant, enseignant);
                        for (Abonnement abonnementExistant : abonnementsExistants) {
                            if (isMemeAbonnement(abonnementExistant, abonnement)) {
                                throw new RuntimeException("L'étudiant est déjà abonné à cet enseignant avec une date d'abonnement dans la limite d'un an.");
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
                    throw new RuntimeException("La classe de l'enseignant est null.");
                }
            } else {
                // L'étudiant est déjà abonné, vous pouvez gérer cette situation selon vos besoins
                throw new RuntimeException("L'étudiant est déjà abonné.");
            }
        } else {
            throw new NotFoundException("Etudiant non trouvé.");
        }
    }

    private boolean isMemeAbonnement(Abonnement abonnement1, Abonnement abonnement2) {
        // Comparer les attributs nécessaires pour déterminer si les abonnements sont les mêmes
        boolean memesAbonnements = Objects.equals(abonnement1.getEtudiant(), abonnement2.getEtudiant())
                && Objects.equals(abonnement1.getEnseignant(), abonnement2.getEnseignant())
                && Objects.equals(abonnement1.getDateAbonnement(), abonnement2.getDateAbonnement());

        if (memesAbonnements) {
            // Comparer si la différence de temps entre les deux abonnements est supérieure à un an
            LocalDate dateAbonnement1 = abonnement1.getDateAbonnement().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate dateAbonnement2 = abonnement2.getDateAbonnement().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            long differenceEnJours = ChronoUnit.DAYS.between(dateAbonnement1, dateAbonnement2);

            return differenceEnJours <= 365; // Autoriser le réabonnement si la différence est inférieure ou égale à un an
        }
        return false;
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
