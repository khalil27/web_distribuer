package com.tn.skillexchange.skillexchange.repository;

import com.tn.skillexchange.skillexchange.entity.PointHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PointHistoryRepository extends JpaRepository<PointHistory, Long> {
    List<PointHistory> findByUserIdOrderByDateDesc(Long userId);
}