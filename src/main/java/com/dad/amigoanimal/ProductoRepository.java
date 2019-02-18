package com.dad.amigoanimal;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



public interface ProductoRepository extends JpaRepository <Producto, Long>{
	Optional<Producto> findById(Long id);
	List<Producto> findByName(String name);
	List<Producto> findByCategory (String category);
	List<Producto> findByPriceBetween(float price1, float price2);
	
	
		
}
