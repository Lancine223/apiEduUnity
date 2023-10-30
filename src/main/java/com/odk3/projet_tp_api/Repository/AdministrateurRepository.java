package com.odk3.projet_tp_api.Repository;

import com.odk3.projet_tp_api.model.Administrateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministrateurRepository extends JpaRepository<Administrateur, Integer> {
    public AdministrateurRepository findByEmail(String email);

    public AdministrateurRepository findByEmailAndMotDePasse(String email,String mon_de_passe);

    public AdministrateurRepository findByIdAdministrateur(int id);
}
