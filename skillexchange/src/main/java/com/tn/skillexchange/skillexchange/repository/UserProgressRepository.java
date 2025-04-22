package com.tn.skillexchange.skillexchange.repository;

import com.tn.skillexchange.skillexchange.entity.UserProgress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserProgressRepository extends JpaRepository<UserProgress, Long> {
    Optional<UserProgress> findByUserId(Long userId);
    List<UserProgress> findAllByOrderByPointsDesc();

}