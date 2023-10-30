package com.odk3.projet_tp_api.Repository;

import com.odk3.projet_tp_api.model.Correction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CorrectionRepository extends JpaRepository<Correction, Integer> {



}
