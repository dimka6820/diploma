package com.dmma.diploma.repository;

import com.dmma.diploma.model.security.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
