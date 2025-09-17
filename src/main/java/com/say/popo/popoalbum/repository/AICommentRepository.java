package com.say.popo.popoalbum.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.say.popo.popoalbum.entity.AIComment;
import com.say.popo.popoalbum.entity.Post;

public interface AICommentRepository extends JpaRepository<AIComment, Long> {
	
	AIComment findByPost(Post post);

}
