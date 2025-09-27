package com.say.popo.snapshelf.repository;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.say.popo.snapshelf.entity.Product;
import com.say.popo.snapshelf.entity.Users;


public interface ProductRepository extends JpaRepository<Product, Long> {

	Product findTopByOrderByIdDesc();

	List<Product> findByUser(Users user);

}
