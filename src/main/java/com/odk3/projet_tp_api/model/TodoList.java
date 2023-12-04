package com.odk3.projet_tp_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class TodoList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private  int idTodoList;

    @NotNull(message = "veuillez entrer la date")
    @Column(nullable = false)
    private Date datetache;


    @NotNull(message = "veuillez entrer une description")
    @Column(nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "idEtudiant", nullable = false)
    private Etudiant etudiant;
}
