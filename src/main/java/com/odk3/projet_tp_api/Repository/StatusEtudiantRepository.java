package com.odk3.projet_tp_api.Repository;

import com.odk3.projet_tp_api.model.StatusEtudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusEtudiantRepository extends JpaRepository<StatusEtudiant, Integer> {
    public StatusEtudiant findByIdStatusEtudiant(int idStatusEtudiant);
    public StatusEtudiant findByEtudiantIdEtudiant(int idEtudiant);
}
