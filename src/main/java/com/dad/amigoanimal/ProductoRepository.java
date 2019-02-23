package com.dad.amigoanimal;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



public interface ProductoRepository extends JpaRepository <Producto, Long>{
	Optional<Producto> findById(Long id);
	List<Producto> findByName(String name);
	List<Producto> findByCategory (String category);
	List<Producto> findByPriceBetween(int price1, int price2);
	List<Producto> findByNameAndCategory(String name, String category);
	List<Producto> findByNameAndPriceBetween(String name, int price1, int price2);
	List<Producto> findByCategoryAndPriceBetween(String category, int price1,int price2);
	List<Producto> findByNameAndCategoryAndPriceBetween(String name, String category, int price1, int price2);
	
}
