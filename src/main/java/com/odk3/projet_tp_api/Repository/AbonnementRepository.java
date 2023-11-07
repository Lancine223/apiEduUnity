package com.odk3.projet_tp_api.Repository;

import com.odk3.projet_tp_api.model.Abonnement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AbonnementRepository extends JpaRepository<Abonnement, Integer> {

     List<Abonnement> findByEtudiantIdEtudiant(int idEtudiant);
}
