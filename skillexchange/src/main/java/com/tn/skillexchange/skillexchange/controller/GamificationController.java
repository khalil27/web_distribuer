package com.tn.skillexchange.skillexchange.controller;

import com.tn.skillexchange.skillexchange.entity.Badge;
import com.tn.skillexchange.skillexchange.entity.PointHistory;
import com.tn.skillexchange.skillexchange.entity.User;
import com.tn.skillexchange.skillexchange.entity.UserProgress;
import com.tn.skillexchange.skillexchange.service.GamificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/gamification")
public class GamificationController {

    private final GamificationService gamificationService;

    // Constructeur manuel pour l'injection de dépendances
    public GamificationController(GamificationService gamificationService) {
        this.gamificationService = gamificationService;
    }

    @PostMapping("/assign-badge")
    public String assignBadge(@RequestParam Long userId,
                              @RequestParam String title,
                              @RequestParam String description) {
        gamificationService.assignBadge(userId, title, description);
        return "Badge attribué avec succès et email envoyé !";
    }



    // ➕ Ajouter des points
    @PostMapping("/points")
    public ResponseEntity<String> addPoints(@RequestBody PointHistory request) {
        gamificationService.addPointsToUser(request.getUserId(), request.getPointsEarned(), request.getReason());
        gamificationService.autoAssignBadge(request.getUserId());
        return ResponseEntity.ok("Points ajoutés avec succès !");
    }

    // 🏅 Lister les badges d’un utilisateur
    @GetMapping("/badges/{userId}")
    public ResponseEntity<List<Badge>> getBadges(@PathVariable Long userId) {
        return ResponseEntity.ok(gamificationService.getBadgesByUser(userId));
    }

    // 📈 Afficher la progression
    @GetMapping("/progress/{userId}")
    public ResponseEntity<UserProgress> getProgress(@PathVariable Long userId) {
        UserProgress progress = gamificationService.getProgressByUser(userId);
        if (progress == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(progress);
    }

    // 📜 Historique des points
    @GetMapping("/history/{userId}")
    public ResponseEntity<List<PointHistory>> getHistory(@PathVariable Long userId) {
        return ResponseEntity.ok(gamificationService.getHistoryByUser(userId));
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return gamificationService.getAllUsers();
    }
    @GetMapping("/badges")
    public List<Badge> getAllBadges() {
        return gamificationService.getAllBadges();
    }
    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user) {
        // Ici, tu peux appeler un service pour ajouter un utilisateur dans la base de données
        User savedUser = gamificationService.addUser(user); // Ajoute une méthode addUser dans le service
        return ResponseEntity.ok(savedUser);
    }

    @GetMapping("/leaderboard")
    public ResponseEntity<List<UserProgress>> getLeaderboard() {
        List<UserProgress> leaderboard = gamificationService.getLeaderboard();
        return ResponseEntity.ok(leaderboard);
    }
    @GetMapping("/history")
    public List<PointHistory> getAllHistory() {
        return gamificationService.getAllHistory();
    }




}
