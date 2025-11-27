package com.zensar.stockapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zensar.stockapp.entity.StockEntity;

public interface StockRepository extends JpaRepository<StockEntity, Integer>{

}
