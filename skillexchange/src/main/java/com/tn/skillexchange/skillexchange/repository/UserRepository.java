package com.tn.skillexchange.skillexchange.repository;

import com.tn.skillexchange.skillexchange.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

