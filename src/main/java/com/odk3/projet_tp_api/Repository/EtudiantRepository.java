package com.odk3.projet_tp_api.Repository;

import com.odk3.projet_tp_api.model.Classe;
import com.odk3.projet_tp_api.model.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant, Integer> {
    public Etudiant findByTelephone(String telephone);

    public Etudiant findByTelephoneAndMotDePasse(String telephone, String motDePasse);

    public Etudiant findByIdEtudiant(int id);
    public Etudiant findByClasse(Classe classe);

}
