package com.odk3.projet_tp_api.Repository;

import com.odk3.projet_tp_api.model.Notation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotationRepository extends JpaRepository<Notation, Integer> {
    public List<Notation> findByEnseignantIdEnseignant(int idEnseignant);
}
