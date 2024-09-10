package fr.maxime.exo_spring_02.controller;

import fr.maxime.exo_spring_02.model.Etudiant;
import fr.maxime.exo_spring_02.service.EtudiantService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
public class EtudiantController {
    private final EtudiantService etudiantService;

    public EtudiantController(EtudiantService etudiantService) {
        this.etudiantService = etudiantService;
    }

    @RequestMapping("/")
    public String index() {
        return "Accueil";
    }

    @RequestMapping("/detail")
    public String detail() {
        return "DetailEtudiant";
    }

    @RequestMapping("/inscription")
    public String inscription() {
        return "Inscription";
    }

    @RequestMapping("/list")
    public String listEtudiant(Model model) {
        model.addAttribute("etudiants", etudiantService.getAllEtudiants());
        return "ListEtudiants";
    }

    @RequestMapping("/search")
    public String rechercheEtudiant(Model model,
                                    @RequestParam(name = "etudiantName", required = false) String name) {
        Etudiant etudiant = etudiantService.getEtudiantByName(name);
        if (etudiant != null) {
            model.addAttribute("etudiant", etudiant);
            return "DetailEtudiant";
        }
        return "RechercheEtudiant";
    }

    @RequestMapping("/inscription/newEtudiant")
    public String inscription(@RequestParam(name = "nom", required = false) String nom,
                              @RequestParam(name = "prenom", required = false) String prenom,
                              @RequestParam(name = "age", required = false) int age,
                              @RequestParam(name = "mail", required = false) String mail,
                              Model model) {
        Etudiant newEtudiant = Etudiant.builder()
                .id(UUID.randomUUID())
                .nom(nom)
                .prenom(prenom)
                .age(age)
                .email(mail)
                .build();
        etudiantService.addEtudiant(newEtudiant);
        model.addAttribute("etudiants", etudiantService.getAllEtudiants());
        return "ListEtudiants";
    }

    @RequestMapping("/detail/{idContact}")
    public String contactPage(Model model, @PathVariable("idContact") UUID id) {
        model.addAttribute("etudiant", etudiantService.getEtudiantById(id));
        Etudiant etudiant = etudiantService.getEtudiantById(id);
        if (etudiant != null) {
            model.addAttribute("etudiant", etudiant);
            return "DetailEtudiant";
        }
        return "DetailEtudiant";
    }

    @RequestMapping("/detail/name{etudiantName}")
    public String contactPage(Model model, @PathVariable("etudiantName") String name) {
        Etudiant etudiant = etudiantService.getEtudiantByName(name);
        if (etudiant != null) {
            model.addAttribute("etudiant", etudiant);
            return "DetailEtudiant";
        }
        return "DetailEtudiant";
    }


    @RequestMapping("/recherche")
    public String contactForm(Model model){
        model.addAttribute("etudiant", new Etudiant());
        return "RechercheEtudiant";
    }
    @PostMapping("/recherche/name")
    public String submitContact(@ModelAttribute("name") String name){
        Etudiant etudiantSearch = etudiantService.getEtudiantByName(name);
        System.out.println(etudiantSearch);
        if(etudiantSearch != null){
            return "redirect:/detail/name" + etudiantSearch.getNom();
        }
        else {
            return "redirect:/recherche";
        }
//        if(contact != null){
//
//        }
    }

}
