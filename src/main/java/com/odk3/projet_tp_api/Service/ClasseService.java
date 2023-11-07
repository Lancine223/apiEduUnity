package com.odk3.projet_tp_api.Service;

import com.odk3.projet_tp_api.Repository.ClasseRepository;
import com.odk3.projet_tp_api.exception.DuplicateException;
import com.odk3.projet_tp_api.exception.NoContentException;
import com.odk3.projet_tp_api.exception.NotFoundException;
import com.odk3.projet_tp_api.model.Classe;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClasseService {
    @Autowired
    ClasseRepository classeRepository;


    public Classe creerClasse(Classe classe){
        if (classeRepository.findByIdClasse(classe.getIdClasse()) == null) {
            return classeRepository.save(classe);
        } else {
            throw new DuplicateException("Cet Classe existe déjà");
        }

    }

    public List<Classe> listClasses() {
        if (!classeRepository.findAll().isEmpty())
            return classeRepository.findAll();
        else
            throw new NoContentException("Aucun Classe n'a été trouver");
    }
    public Classe modifierClasse(Classe classe) {

        if (classeRepository.findByIdClasse(classe.getIdClasse()) != null){
            return classeRepository.save(classe);
        }
        else {
            throw  new NotFoundException("Cet Classe n'existe pas");
        }

    }
    //++++++++++++++++++++++++
    public List<Classe> ListClasseParFiliere(int idFiliere){

        // Obtention de tous les budget dans la base de donnÃ©es
        List<Classe> ListClasses = classeRepository.findByFiliereIdFiliere(idFiliere);

        // Si la liste est vide, le systÃ¨me lÃ¨vera une exception
        if (ListClasses.isEmpty())
            throw new EntityNotFoundException("Aucun Classe trouvé");

        // Dans le cas contraire le systÃ¨me retourne la liste
        return ListClasses;
    }
    //+++++++++
    public String supprimeClasse(Classe classe) {

        if (classeRepository.findByIdClasse(classe.getIdClasse()) != null) {
            classeRepository.delete(classe);
            return "Succès";
        } else {
            throw  new NotFoundException("Cet Classe n'existe pas");
        }

    }

}
