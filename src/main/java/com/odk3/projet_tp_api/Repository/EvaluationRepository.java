package com.odk3.projet_tp_api.Repository;

import com.odk3.projet_tp_api.model.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvaluationRepository extends JpaRepository<Evaluation, Integer> {

}
