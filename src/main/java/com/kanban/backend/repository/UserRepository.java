package com.kanban.backend.repository;

import java.util.List;
import java.util.Optional;

//src/main/java/com/example/demo/repository/UserRepository.java

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kanban.backend.User;

public interface UserRepository extends JpaRepository<User, Long> {

	@Query("SELECT COUNT(u) > 0 FROM User u WHERE u.email = :email")
    boolean existsByEmail(@Param("email") String email);
	
	
	@Query("SELECT u.email FROM User u WHERE u.email = :email AND u.password = :password")
    <Optional> String findByUsernameOrEmail(@Param("email") String email, @Param("password") String password);

	@Query("SELECT u FROM User u WHERE u.email =:email")

	Optional<User> findByUsername(@Param("email")String username);

}

