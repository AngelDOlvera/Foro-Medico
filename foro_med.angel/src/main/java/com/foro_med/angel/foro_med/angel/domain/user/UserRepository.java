package com.foro_med.angel.foro_med.angel.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
public interface UserRepository extends JpaRepository<User, Long> {
    User findByLogin(String username);
}
