package fr.maxime.exo_spring_02.service;

import fr.maxime.exo_spring_02.model.Etudiant;
import org.springframework.stereotype.Service;

import java.rmi.server.UID;
import java.util.*;

@Service
public class EtudiantService {
    private final Map<UUID, Etudiant> listEtudiants;

    public EtudiantService() {
        this.listEtudiants = new HashMap<UUID, Etudiant>();
    }

    public List<Etudiant> getAllEtudiants() {
        return listEtudiants.values().stream().toList();
    }

    public Etudiant getEtudiantById(UUID id) {
        return listEtudiants.get(id);
    }
    public Etudiant getEtudiantByName(String name) {
        return listEtudiants.values().stream().filter(e -> e.getNom().equals(name)).findFirst().orElse(null);
    }

    public Etudiant addEtudiant(Etudiant etudiant) {
        Etudiant newEtudiant = Etudiant.builder()
                .id(UUID.randomUUID())
                .nom(etudiant.getNom())
                .prenom(etudiant.getPrenom())
                .age(etudiant.getAge())
                .email(etudiant.getEmail())
                .build();
        listEtudiants.put(newEtudiant.getId(), newEtudiant);
        return newEtudiant;
    }

    public Etudiant updateEtudiant(UUID id, Etudiant etudiant) {
        Etudiant existingEtudiant = listEtudiants.get(id);
        if (existingEtudiant!=null) {
            existingEtudiant.setNom(etudiant.getNom());
            existingEtudiant.setPrenom(etudiant.getPrenom());
            existingEtudiant.setAge(etudiant.getAge());
            existingEtudiant.setEmail(etudiant.getEmail());
            return existingEtudiant;
        }
        return null;

    }

    public boolean deleteEtudiant(Etudiant etudiant) {
        return listEtudiants.remove(etudiant.getId())!=null;
    }
}
