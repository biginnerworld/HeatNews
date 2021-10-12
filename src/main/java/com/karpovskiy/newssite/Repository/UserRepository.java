package com.karpovskiy.newssite.Repository;

import com.karpovskiy.newssite.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findUserEntityByUsername(String username);
    UserEntity findUserEntityById(String id);
}
