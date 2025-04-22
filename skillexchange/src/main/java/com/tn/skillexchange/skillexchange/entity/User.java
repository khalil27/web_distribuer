package com.tn.skillexchange.skillexchange.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "app_user") // ou "users"
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;

    public User(Long id, String email) {
        this.id = id;
        this.email = email;
    }

    public User(Long id) {
        this.id = id;
    }

    public User() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                '}';
    }
}
