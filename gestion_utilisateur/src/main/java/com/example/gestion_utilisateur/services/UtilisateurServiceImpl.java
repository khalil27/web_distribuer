package com.example.gestion_utilisateur.services;

import com.example.gestion_utilisateur.entites.Utilisateur;
import com.example.gestion_utilisateur.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurServiceImpl implements UtilisateurService{
    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurRepository.findAll();
    }

    @Override
    public Optional<Utilisateur> getUtilisateurById(Long id) {
        return utilisateurRepository.findById(id);
    }

    @Override
    public Utilisateur createUtilisateur(Utilisateur utilisateur) {
        if (utilisateurRepository.existsByLogin(utilisateur.getLogin())) {
            throw new IllegalArgumentException("Login deja utiliser");
        }
        utilisateur.setActive(true);
        utilisateur.setStatus(false); // Status initial
        return utilisateurRepository.save(utilisateur);
    }

    @Override
    public Utilisateur updateUtilisateur(Long id, Utilisateur utilisateur) {
        if (!utilisateurRepository.existsById(id)) {
            throw new IllegalArgumentException("Utilisateur non trouvé avec l'ID: " + id);
        }
        utilisateur.setId(id);
        return utilisateurRepository.save(utilisateur);
    }
    @Override
    public Utilisateur updateMdpUtilisateur(Long id, String nouveauMdp) {
        Optional<Utilisateur> optionalUtilisateur = utilisateurRepository.findById(id);
        if (!optionalUtilisateur.isPresent()) {
            throw new IllegalArgumentException("Utilisateur non trouvé avec l'ID: " + id);
        }
        Utilisateur utilisateur = optionalUtilisateur.get();
        utilisateur.setPassword(nouveauMdp);
        return utilisateurRepository.save(utilisateur);
    }

    /*@Override
    public void deleteUtilisateur(Long id) {
        if (!utilisateurRepository.existsById(id)) {
            throw new IllegalArgumentException("Utilisateur non trouvé avec l'ID: " + id);
        }
        utilisateurRepository.deleteById(id);
    }*/
    @Override
    public void deleteUtilisateur(Long id) {
        Optional<Utilisateur> optionalUtilisateur = utilisateurRepository.findById(id);
        if (!optionalUtilisateur.isPresent()) {
            throw new IllegalArgumentException("Utilisateur non trouvé avec l'ID: " + id);
        }
        Utilisateur utilisateur = optionalUtilisateur.get();
        utilisateur.setActive(false);
        utilisateurRepository.save(utilisateur);
    }


}
