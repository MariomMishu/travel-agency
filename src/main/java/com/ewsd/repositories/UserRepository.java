package com.ewsd.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ewsd.model.User;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long>{
	 Optional<User> findByUsername(String username);
		User findByusername(String name);
		User findByEmail(String email);
}
