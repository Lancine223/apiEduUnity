package com.odk3.projet_tp_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiscussionPodjo {

    private String message;

    private int forum;

    private int etudiant;

    private int enseignant;
}
