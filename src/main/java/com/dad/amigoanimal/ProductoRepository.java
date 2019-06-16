package com.dad.amigoanimal;

import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@CacheConfig (cacheNames="productos")
public interface ProductoRepository extends JpaRepository <Producto, Long>{
	@CacheEvict(allEntries=true)
	Producto save (Producto producto);
	@CacheEvict(allEntries=true)
	void delete (Producto producto);
	@Cacheable
	Page<Producto> findByName(String name, Pageable page);
	@Cacheable
	Page<Producto> findByCategory (String category, Pageable page);
	@Cacheable
	Page<Producto> findByPriceBetween(int price1, int price2, Pageable page);
	@Cacheable
	Page<Producto> findByNameAndCategory(String name, String category, Pageable page);
	@Cacheable
	Page<Producto> findByNameAndPriceBetween(String name, int price1, int price2, Pageable page);
	@Cacheable
	Page<Producto> findByCategoryAndPriceBetween(String category, int price1,int price2, Pageable page);
	@Cacheable
	Page<Producto> findByNameAndCategoryAndPriceBetween(String name, String category, int price1, int price2, Pageable page);
	
}
