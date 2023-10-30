package com.odk3.projet_tp_api.Repository;

import com.odk3.projet_tp_api.model.Cours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoursRepository extends JpaRepository<Cours, Integer> {


    public List<Cours> findByEnseignantIdEnseignant(int idEnseignant);
}
