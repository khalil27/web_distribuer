package com.tn.skillexchange.skillexchange.service;

import com.tn.skillexchange.skillexchange.entity.Badge;
import com.tn.skillexchange.skillexchange.entity.PointHistory;
import com.tn.skillexchange.skillexchange.entity.User;
import com.tn.skillexchange.skillexchange.entity.UserProgress;
import com.tn.skillexchange.skillexchange.repository.BadgeRepository;
import com.tn.skillexchange.skillexchange.repository.PointHistoryRepository;
import com.tn.skillexchange.skillexchange.repository.UserProgressRepository;
import com.tn.skillexchange.skillexchange.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class GamificationService {

    private final UserProgressRepository userProgressRepository;
    private final PointHistoryRepository pointHistoryRepository;
    private final BadgeRepository badgeRepository;
    private final UserRepository userRepository;
    private final EmailService emailService;

    // ✅ Seul constructeur à conserver
    public GamificationService(UserProgressRepository userProgressRepository,
                               PointHistoryRepository pointHistoryRepository,
                               BadgeRepository badgeRepository,
                               UserRepository userRepository,
                               EmailService emailService) {
        this.userProgressRepository = userProgressRepository;
        this.pointHistoryRepository = pointHistoryRepository;
        this.badgeRepository = badgeRepository;
        this.userRepository = userRepository;
        this.emailService = emailService;
    }

    public void addPointsToUser(Long userId, int points, String reason) {
        // Récupération de la progression utilisateur ou création
        UserProgress progress = userProgressRepository.findByUserId(userId)
                .orElse(new UserProgress(userId, 0, 0));


        int previousLevel = progress.getLevel();
        progress.addPoints(points);
        System.out.println("📌 progress = " + progress);
        userProgressRepository.save(progress);

        // Historique des points
        PointHistory history = new PointHistory(userId, points, reason, LocalDateTime.now());
        System.out.println("📌 point history = " + history);
        pointHistoryRepository.save(history);

        // Vérifier si un nouveau badge doit être attribué
        if (progress.getLevel() > previousLevel) {
            assignBadge(userId, "Niveau " + progress.getLevel(), "Atteint le niveau " + progress.getLevel());
        }
    }

    public void assignBadge(Long userId, String title, String description) {
        System.out.println("📌 assignBadge lancé pour userId = " + userId);

        Badge badge = new Badge(userId, title, description, LocalDateTime.now());
        badgeRepository.save(badge);
        System.out.println("✅ Badge sauvegardé : " + badge.getTitle());

        userRepository.findById(userId).ifPresentOrElse(user -> {
            System.out.println("🔍 Utilisateur trouvé : " + user.getEmail());
            if (user.getEmail() != null) {
                emailService.sendBadgeEmail(user.getEmail(), title, description);
            } else {
                System.out.println("⚠️ L'utilisateur n'a pas d'adresse email !");
            }
        }, () -> System.out.println("❌ Aucun utilisateur trouvé avec l'id " + userId));
    }

    public List<Badge> getBadgesByUser(Long userId) {
        return badgeRepository.findByUserId(userId);
    }

    public List<PointHistory> getHistoryByUser(Long userId) {
        return pointHistoryRepository.findByUserIdOrderByDateDesc(userId);
    }

    public UserProgress getProgressByUser(Long userId) {
        return userProgressRepository.findByUserId(userId).orElse(null);
    }


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public List<Badge> getAllBadges() {
        return badgeRepository.findAll();
    }
    public User addUser(User user) {
        return userRepository.save(user);
    }

    public void autoAssignBadge(Long userId) {
        UserProgress progress = getProgressByUser(userId);
        if (progress.getPoints() >= 1000 && !hasBadge(userId, "Badge 1000 points")) {
            assignBadge(userId, "Badge 1000 points", "Atteint 1000 points !");
        }
    }

    private boolean hasBadge(Long userId, String title) {
        return badgeRepository.existsByUserIdAndTitle(userId, title);
    }


    public List<UserProgress> getLeaderboard() {
        return userProgressRepository.findAllByOrderByPointsDesc();
    }

    public List<PointHistory> getAllHistory() {
        return pointHistoryRepository.findAll();
    }

}
