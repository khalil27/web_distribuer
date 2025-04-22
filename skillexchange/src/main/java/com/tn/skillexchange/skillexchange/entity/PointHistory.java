package com.tn.skillexchange.skillexchange.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class PointHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private int pointsEarned;

    private String reason; // ex : "completed a course", "shared a skill"

    private LocalDateTime date;

    // Constructeur sans arguments
    public PointHistory() {
    }

    // Constructeur avec tous les champs
    public PointHistory(Long userId, int pointsEarned, String reason, LocalDateTime date) {
        this.userId = userId;
        this.pointsEarned = pointsEarned;
        this.reason = reason;
        this.date = date;
    }

    // Getters et Setters manuels
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public int getPointsEarned() {
        return pointsEarned;
    }

    public void setPointsEarned(int pointsEarned) {
        this.pointsEarned = pointsEarned;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    // Méthode toString manuelle (si nécessaire)
    @Override
    public String toString() {
        return "PointHistory{" +
                "id=" + id +
                ", userId=" + userId +
                ", pointsEarned=" + pointsEarned +
                ", reason='" + reason + '\'' +
                ", date=" + date +
                '}';
    }
}
