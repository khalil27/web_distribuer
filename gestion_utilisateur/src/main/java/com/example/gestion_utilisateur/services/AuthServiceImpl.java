package com.example.gestion_utilisateur.services;

import com.example.gestion_utilisateur.entites.Utilisateur;
import com.example.gestion_utilisateur.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService{
    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Override
    public Utilisateur login(String username, String password) {
        Optional<Utilisateur> utilisateurOptional = utilisateurRepository.findByLogin(username);
        if (utilisateurOptional.isPresent()) {
            Utilisateur utilisateur = utilisateurOptional.get();
            if (utilisateur.isActive() && passwordMatches(utilisateur, password)) {
                // Mettre à jour le statut de l'utilisateur après la connexion réussie
                utilisateur.setStatus(true);
                return utilisateurRepository.save(utilisateur);
            }
        }
        return null; // Retourne null si l'authentification échoue
    }

    private boolean passwordMatches(Utilisateur utilisateur, String password) {
        // Vérifie si le mot de passe fourni correspond au mot de passe de l'utilisateur dans la base de données
        return utilisateur.getPassword().equals(password);
    }

    @Override
    public void deconnexion(Long id) {
        Optional<Utilisateur> optionalUtilisateur = utilisateurRepository.findById(id);
        if (optionalUtilisateur.isPresent()) {
            Utilisateur utilisateur = optionalUtilisateur.get();
            utilisateur.setStatus(false); // Déconnexion de l'utilisateur en mettant son statut à false
            utilisateurRepository.save(utilisateur);
        } else {
            throw new IllegalArgumentException("Utilisateur non trouvé avec l'ID: " + id);
        }
    }
}
