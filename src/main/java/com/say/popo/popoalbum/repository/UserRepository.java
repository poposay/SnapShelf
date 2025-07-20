package com.say.popo.popoalbum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.say.popo.popoalbum.entity.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
	
	Users findByEmail(String email);

}
