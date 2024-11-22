package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import com.domain.User;

import java.util.List;
import java.util.Optional;

@EnableJpaRepositories
@Repository
public interface UserRepository extends JpaRepository<User,Integer>
{
    Optional<User> findOneByEmailAndPassword(String email, String password);
    Optional<User> findByEmail(String email);
    
    List<User> findUserByEmail(String email);

    boolean existsByEmail(String email);
    
}
