package com.odk3.projet_tp_api.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnseignantRepository extends JpaRepository<Enseignant, Integer> {
    public Enseignant findByEmail(String email);

    public Enseignant findByEmailAndMotDePasse(String email, String mot_de_passe);

    public Enseignant findByIdEnseignant(int idEnseignant);
}
