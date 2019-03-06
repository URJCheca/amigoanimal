package com.dad.amigoanimal;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



public interface ProductoRepository extends JpaRepository <Producto, Long>{
	
	Page<Producto> findByName(String name, Pageable page);
	Page<Producto> findByCategory (String category, Pageable page);
	Page<Producto> findByPriceBetween(int price1, int price2, Pageable page);
	Page<Producto> findByNameAndCategory(String name, String category, Pageable page);
	Page<Producto> findByNameAndPriceBetween(String name, int price1, int price2, Pageable page);
	Page<Producto> findByCategoryAndPriceBetween(String category, int price1,int price2, Pageable page);
	Page<Producto> findByNameAndCategoryAndPriceBetween(String name, String category, int price1, int price2, Pageable page);
	
}
