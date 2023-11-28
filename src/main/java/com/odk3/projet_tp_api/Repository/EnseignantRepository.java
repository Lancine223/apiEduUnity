package com.odk3.projet_tp_api.Repository;

import com.odk3.projet_tp_api.model.Enseignant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnseignantRepository extends JpaRepository<Enseignant, Integer> {
    public Enseignant findByEmail(String email);

    public Enseignant findByEmailAndMotDePasse(String email, String mot_de_passe);
    List<Enseignant> findByClasseIdClasse(int idEnseignant);
    public Enseignant findByIdEnseignant(int idEnseignant);
}
