package com.tn.skillexchange.skillexchange.entity;

import jakarta.persistence.*;

@Entity
public class UserProgress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private int points;
    private int level;

    // Constructeur sans arguments
    public UserProgress() {
    }

    // Constructeur avec tous les champs
    public UserProgress(Long userId, int points, int level) {
        this.userId = userId;
        this.points = points;
        this.level = level;
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

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    // Méthode pour ajouter des points et ajuster le niveau
    public void addPoints(int earnedPoints) {
        this.points += earnedPoints;
        this.level = this.points / 100;
    }

    // Méthode toString manuelle (si besoin)
    @Override
    public String toString() {
        return "UserProgress{" +
                "id=" + id +
                ", userId=" + userId +
                ", points=" + points +
                ", level=" + level +
                '}';
    }


}
