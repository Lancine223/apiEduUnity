package com.odk3.projet_tp_api.Repository;

import com.odk3.projet_tp_api.model.Classe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ClasseRepository extends JpaRepository<Classe, Integer> {
    public List<Classe> findByFiliereIdFiliere(int idFiliere);
    public Classe findByIdClasse(int idClasse);
}
