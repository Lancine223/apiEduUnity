package com.odk3.projet_tp_api.Repository;

import com.odk3.projet_tp_api.model.Abonnement;
import com.odk3.projet_tp_api.model.Enseignant;
import com.odk3.projet_tp_api.model.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AbonnementRepository extends JpaRepository<Abonnement, Integer> {

     List<Abonnement> findByEtudiantIdEtudiant(int idEtudiant);
     List<Abonnement> findByEnseignantIdEnseignant(int idEnseignant);
     //List<Abonnement> getAbonnementsByEtudiantId(int idEtudiant);
     List<Abonnement> findByEtudiantAndEnseignant(Etudiant etudiant, Enseignant enseignant);
     @Query("SELECT COUNT(a.etudiant.idEtudiant) FROM Abonnement a")
     int countTotalAbonne();
}
