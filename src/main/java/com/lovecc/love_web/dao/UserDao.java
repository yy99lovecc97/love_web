package com.lovecc.love_web.dao;

import com.lovecc.love_web.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User,Integer> {
    User findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByOnlyId(String only_id);
}
