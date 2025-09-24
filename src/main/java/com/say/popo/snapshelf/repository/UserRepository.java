package com.say.popo.snapshelf.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.say.popo.snapshelf.entity.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
	
	Optional<Users> findByEmail(String email);
	boolean existsByEmail(String email);
}
