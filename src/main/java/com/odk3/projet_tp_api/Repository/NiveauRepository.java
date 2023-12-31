package com.odk3.projet_tp_api.Repository;

import com.odk3.projet_tp_api.model.Niveau;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NiveauRepository extends JpaRepository<Niveau, Integer> {
    public Niveau findByIdNiveau(int idNiveau);

}
