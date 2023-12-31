package com.odk3.projet_tp_api.Repository;

import com.odk3.projet_tp_api.model.Apropos;
import com.odk3.projet_tp_api.model.Enseignant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AproposRepository extends JpaRepository<Apropos, Integer> {

    public Apropos findByIdApropos(int idApropos);
    Apropos findByEnseignant(Enseignant enseignant);
    public Apropos findByEnseignantIdEnseignant(int idEnseignant);

}
