package com.vuejs.backend.domain.user.dao;

import com.vuejs.backend.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByName(String username);
}
