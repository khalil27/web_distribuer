package com.tn.skillexchange.skillexchange.repository;

import com.tn.skillexchange.skillexchange.entity.Badge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BadgeRepository extends JpaRepository<Badge, Long> {
    List<Badge> findByUserId(Long userId);
    boolean existsByUserIdAndTitle(Long userId, String title);

}