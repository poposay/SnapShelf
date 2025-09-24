package com.say.popo.snapshelf.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.say.popo.snapshelf.entity.AIDescription;
import com.say.popo.snapshelf.entity.Product;

public interface AIDescriptiontRepository extends JpaRepository<AIDescription, Long> {
	
	AIDescription findByProduct(Product product);

}
