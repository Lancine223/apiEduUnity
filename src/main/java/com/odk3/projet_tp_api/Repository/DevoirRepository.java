package com.odk3.projet_tp_api.Repository;

import com.odk3.projet_tp_api.model.Devoir;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DevoirRepository extends JpaRepository<Devoir, Integer> {

    public List<Devoir> findByEnseignantIdEnseignant (int idEnseignant);

}
