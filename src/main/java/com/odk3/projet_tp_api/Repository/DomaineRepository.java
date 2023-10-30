package com.odk3.projet_tp_api.Repository;

import com.odk3.projet_tp_api.model.Domaine;
import com.odk3.projet_tp_api.model.Videos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DomaineRepository extends JpaRepository<Domaine, Integer> {
    public Videos findByIdDomaine(int idDomaine);
}
