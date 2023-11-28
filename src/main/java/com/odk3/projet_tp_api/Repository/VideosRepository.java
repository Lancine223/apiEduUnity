package com.odk3.projet_tp_api.Repository;

import com.odk3.projet_tp_api.model.Videos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideosRepository extends JpaRepository<Videos, Integer> {

    List<Videos> findByEnseignantIdEnseignant(int idEnseignant);
    public Videos findByIdVideos(int idVideos);
    public Videos findByTitreContains(String titre);

}
