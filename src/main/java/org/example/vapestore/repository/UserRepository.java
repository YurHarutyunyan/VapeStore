package org.example.vapestore.repository;

import org.example.vapestore.security.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findUserByUsername(String username);
}
