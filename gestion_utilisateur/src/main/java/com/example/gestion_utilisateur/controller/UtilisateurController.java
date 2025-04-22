package com.example.gestion_utilisateur.controller;

import com.example.gestion_utilisateur.entites.Utilisateur;
import com.example.gestion_utilisateur.services.UtilisateurService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/utilisateurs")
@CrossOrigin(origins = "http://localhost:4200")
@Tag(name = "Gestion des utilisateurs")
public class UtilisateurController {

    @Autowired
    private UtilisateurService utilisateurService;

    @GetMapping
    @Operation(summary = "Récupérer tous les utilisateurs")
    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurService.getAllUtilisateurs();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupérer un utilisateur par son ID")
    public ResponseEntity<Utilisateur> getUtilisateurById(@PathVariable Long id) {
        Optional<Utilisateur> utilisateur = utilisateurService.getUtilisateurById(id);
        return utilisateur.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Créer un nouvel utilisateur")
    public ResponseEntity<Utilisateur> createUtilisateur(@RequestBody Utilisateur utilisateur) {
        Utilisateur createdUtilisateur = utilisateurService.createUtilisateur(utilisateur);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUtilisateur);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Mettre à jour un utilisateur existant")
    public ResponseEntity<Utilisateur> updateUtilisateur(@PathVariable Long id, @RequestBody Utilisateur utilisateur) {
        Utilisateur updatedUtilisateur = utilisateurService.updateUtilisateur(id, utilisateur);
        return ResponseEntity.ok(updatedUtilisateur);
    }

    @PutMapping("/mdp/{id}")
    @Operation(summary = "Mettre à jour le mot de passe d’un utilisateur")
    public ResponseEntity<Utilisateur> updateMdpUtilisateur(@PathVariable Long id, @RequestBody String nouveauMdp) {
        Utilisateur utilisateur = utilisateurService.updateMdpUtilisateur(id, nouveauMdp);
        return ResponseEntity.ok(utilisateur);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un utilisateur par son ID")
    public void deleteUtilisateur(@PathVariable Long id) {
        utilisateurService.deleteUtilisateur(id);
    }
}
