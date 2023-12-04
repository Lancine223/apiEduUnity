package com.odk3.projet_tp_api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Forum {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int idForum;

    @NotNull(message = "le champs titre est vide")
    @Column(nullable = false)
    private String titre;

    @NotNull(message = "le champs date est vide")
    @Column(nullable = false)
    private Date dateForum;

    @ManyToOne
    @JoinColumn(name = "idEnseignant", nullable = false)
    private Enseignant enseignant;

    @OneToMany(mappedBy = "forum", orphanRemoval = true)
    @JsonIgnoreProperties(value = {"forum","enseignant"})
    private List<Discussion> discussionList = new ArrayList<>();

}
