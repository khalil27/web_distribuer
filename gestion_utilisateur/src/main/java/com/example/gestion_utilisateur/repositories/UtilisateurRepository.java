package com.example.gestion_utilisateur.repositories;

import com.example.gestion_utilisateur.entites.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    Optional<Utilisateur> findByLogin(String login);
    boolean existsByLogin(String login);
}
