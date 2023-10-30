package com.odk3.projet_tp_api.Repository;

import com.odk3.projet_tp_api.model.PreinscriptionEnseignant;
import com.odk3.projet_tp_api.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PreinscriptionEnseignantRepository  extends JpaRepository<PreinscriptionEnseignant, Integer> {
    public PreinscriptionEnseignant findByIdPre(int idPreinscrit);
    public PreinscriptionEnseignant findByEmail(String email);
}
