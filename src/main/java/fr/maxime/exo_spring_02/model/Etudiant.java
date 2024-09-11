package fr.maxime.exo_spring_02.model;

import jakarta.validation.constraints.*;
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
    @NotBlank
    @NotNull(message = "Ce champ doit être rempli!")
    @Size(min = 2, message = "Minimum de 2 caractere !")
    private String nom;
    @NotBlank
    @NotNull(message = "Ce champ doit être rempli!")
    @Size(min = 3, message = "Minimum de 3 caractere !")
    private String prenom;
    @Min(18)
    @Max(100)
    private int age;
    private String email;
}
