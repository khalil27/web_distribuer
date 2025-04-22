package com.example.gestion_utilisateur.services;


import com.example.gestion_utilisateur.entites.Utilisateur;

import java.util.List;
import java.util.Optional;

public interface UtilisateurService {

    List<Utilisateur> getAllUtilisateurs();
    Optional<Utilisateur> getUtilisateurById(Long id);
    Utilisateur createUtilisateur(Utilisateur utilisateur);
    Utilisateur updateUtilisateur(Long id, Utilisateur utilisateur);
    Utilisateur updateMdpUtilisateur(Long id, String nouveauMdp);
    void deleteUtilisateur(Long id);


}