package com.odk3.projet_tp_api.Repository;

import com.odk3.projet_tp_api.model.Filiere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FiliereRepository extends JpaRepository<Filiere, Integer> {
    public List<Filiere> findByNiveauIdNiveau(int idNiveau);
    public Filiere findByIdFiliere(int idFiliere);


}
