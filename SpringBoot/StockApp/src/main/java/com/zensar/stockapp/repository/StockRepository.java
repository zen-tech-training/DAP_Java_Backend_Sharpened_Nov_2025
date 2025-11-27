package com.zensar.stockapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.zensar.stockapp.entity.StockEntity;

public interface StockRepository extends JpaRepository<StockEntity, Integer>{

	//Customized finder functions
	List<StockEntity> findByName(String name);
	List<StockEntity> findByMarket(String market);
	List<StockEntity> findByNameAndMarket(String name, String market);
	
	//Equivalent methods
	List<StockEntity> findByNameContaining(String namePattern);
	List<StockEntity> findByNameIsContaining(String namePattern);
	List<StockEntity> findByNameContains(String namePattern);
	@Query(value="SELECT se from StockEntity As se WHERE se.name LIKE '%:namePattern%'") //JPQL
	List<StockEntity> getStocksByNamePattern(String namePattern);
	@Query(value="SELECT * FROM stocks WHERE name LIKE '%:namePattern%'", nativeQuery = true) //SQL
	List<StockEntity> getStocksByNamePatternSQL(String namePattern);
	
	List<StockEntity> findByOrderByName(); //ASC
	List<StockEntity> findByOrderByNameDesc(); //DESC
	
}
