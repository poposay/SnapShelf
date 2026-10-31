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
		       "AND (:keyword IS NULL OR LOWER(p.product_name) LIKE LOWER(CONCAT('%', CAST(:keyword AS string), '%'))) " +
		       "AND (:categoryId IS NULL OR p.category.id = :categoryId)")
		Page<Product> search(@Param("keyword") String keyword,
		                     @Param("categoryId") Long categoryId,
		                     Pageable pageable);
}
