package com.say.popo.popoalbum.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.say.popo.popoalbum.entity.Post;
import com.say.popo.popoalbum.entity.Users;

public interface PostRepository extends JpaRepository<Post, Long> {

	Post findTopByOrderByIdDesc();

	List<Post> findByUser(Users user);

}
