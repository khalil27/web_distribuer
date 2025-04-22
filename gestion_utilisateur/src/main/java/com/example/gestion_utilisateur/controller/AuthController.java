package com.example.gestion_utilisateur.controller;

import com.example.gestion_utilisateur.entites.Utilisateur;
import com.example.gestion_utilisateur.services.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
@Tag(name = "Authentification")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    @Operation(summary = "Authentifier un utilisateur (login)")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");
        Utilisateur utilisateur = authService.login(username, password);

        if (utilisateur != null) {
            Map<String, Object> response = new HashMap<>();
            response.put("id", utilisateur.getId());
            response.put("nom", utilisateur.getNom());
            response.put("prenom", utilisateur.getPrenom());
            response.put("login", utilisateur.getLogin());
            response.put("type", utilisateur.getType());
            response.put("status", utilisateur.isStatus());
            response.put("active", utilisateur.isActive());
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @PutMapping("/deconnexion/{id}")
    @Operation(summary = "Déconnecter un utilisateur par ID")
    public void deconnexion(@PathVariable Long id) {
        authService.deconnexion(id);
    }
}
