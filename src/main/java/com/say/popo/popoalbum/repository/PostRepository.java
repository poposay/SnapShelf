package com.say.popo.popoalbum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.say.popo.popoalbum.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

	Post findTopByOrderByIdDesc();

}
