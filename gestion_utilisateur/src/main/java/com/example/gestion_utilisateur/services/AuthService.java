package com.example.gestion_utilisateur.services;

import com.example.gestion_utilisateur.entites.Utilisateur;

public interface AuthService {
    Utilisateur login(String username, String password);
    void deconnexion(Long id);
}
