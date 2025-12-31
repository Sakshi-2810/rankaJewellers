package com.example.jewelry.repository;

import com.example.jewelry.model.Stocks;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends MongoRepository<Stocks, Long> {
}
