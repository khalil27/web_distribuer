package com.tn.skillexchange.skillexchange.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Badge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String title;

    private String description;

    private LocalDateTime dateAwarded;

    // Constructeur sans arguments
    public Badge() {
    }

    // Constructeur avec tous les champs
    public Badge(Long userId, String title, String description, LocalDateTime dateAwarded) {
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.dateAwarded = dateAwarded;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDateAwarded() {
        return dateAwarded;
    }

    public void setDateAwarded(LocalDateTime dateAwarded) {
        this.dateAwarded = dateAwarded;
    }

    // Méthode toString manuelle (si nécessaire)
    @Override
    public String toString() {
        return "Badge{" +
                "id=" + id +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", dateAwarded=" + dateAwarded +
                '}';
    }
}
