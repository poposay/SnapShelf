package com.say.popo.snapshelf.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.say.popo.snapshelf.entity.Product;
import com.say.popo.snapshelf.entity.Users;


public interface ProductRepository extends JpaRepository<Product, Long> {

	Product findTopByOrderByIdDesc();

	List<Product> findByUser(Users user);
	
	Page<Product> findByPublishedTrue(Pageable pageable);
	
	@Query("SELECT p FROM Product p WHERE p.published = true " +
		       "AND LOWER(p.product_name) LIKE LOWER(CONCAT('%', :keyword, '%'))")
		Page<Product> searchByKeyword(@Param("keyword") String keyword, 
		                               Pageable pageable);
}
