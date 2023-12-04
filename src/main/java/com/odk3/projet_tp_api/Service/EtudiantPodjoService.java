package com.odk3.projet_tp_api.Service;

import com.odk3.projet_tp_api.Repository.ClasseRepository;
import com.odk3.projet_tp_api.Repository.EtudiantRepository;
import com.odk3.projet_tp_api.Repository.FiliereRepository;
import com.odk3.projet_tp_api.Repository.NiveauRepository;
import com.odk3.projet_tp_api.exception.DuplicateException;
import com.odk3.projet_tp_api.model.Etudiant;
import com.odk3.projet_tp_api.model.EtudiantPodjo;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Data
@Service
public class EtudiantPodjoService {

    @Autowired
    private EtudiantService etudiantService;
    @Autowired
    private EtudiantRepository etudiantRepository;
    @Autowired
    private NiveauRepository niveauRepository;
    @Autowired
    private ClasseRepository classeRepository;

    @Autowired
    private FiliereRepository filiereRepository;
    public EtudiantPodjoService(EtudiantService etudiantService,
                                  EtudiantRepository etudiantRepository,
                                  NiveauRepository niveauRepository,
                                  ClasseRepository classeRepository,
                                  FiliereRepository filiereRepository){
        this.etudiantService=etudiantService;
        this.classeRepository = classeRepository;
        this.filiereRepository = filiereRepository;
        this.niveauRepository = niveauRepository;
        this.etudiantRepository = etudiantRepository;
    }
    public String createEtudiantPodjo(EtudiantPodjo etudiantPodjo){
        System.out.println(etudiantPodjo.getTelephone());
        if (etudiantRepository.findByTelephone(etudiantPodjo.getTelephone()) == null) {
            Etudiant etudiant = new Etudiant();
            etudiant.setNom(etudiantPodjo.getNom());
            etudiant.setPrenom(etudiantPodjo.getPrenom());
            etudiant.setTelephone(etudiantPodjo.getTelephone());
            etudiant.setEstAbonner(false);
            etudiant.setAge(etudiantPodjo.getAge());
            etudiant.setMotDePasse(etudiantPodjo.getMotDePasse());
            etudiant.setNiveau(niveauRepository.findByIdNiveau(etudiantPodjo.getNiveau()));
            etudiant.setFiliere(filiereRepository.findByIdFiliere(etudiantPodjo.getFiliere()));
            etudiant.setClasse(classeRepository.findByIdClasse(etudiantPodjo.getClasse()));
            etudiantRepository.save(etudiant);



            return "Inscription reussit avec succès";
        } else {
            throw new DuplicateException("Cet numéro de téléphone existe déjà");
        }
    }


}
