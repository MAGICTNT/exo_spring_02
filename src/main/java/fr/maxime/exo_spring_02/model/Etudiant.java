package fr.maxime.exo_spring_02.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Etudiant {
    private UUID id;
    private String nom;
    private String prenom;
    private int age;
    private String email;
}
