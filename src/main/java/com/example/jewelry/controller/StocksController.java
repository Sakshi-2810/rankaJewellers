package com.example.jewelry.controller;

import com.example.jewelry.model.Response;
import com.example.jewelry.model.Stocks;
import com.example.jewelry.service.StocksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StocksController {
    @Autowired
    StocksService stocksService;

    @GetMapping(produces = "application/json", value = "/stocks")
    public ResponseEntity<Response> getAllStocks() {
        return ResponseEntity.ok(stocksService.fetchAllStocks());
    }

    @PostMapping(produces = "application/json", value = "/stocks/save")
    public ResponseEntity<Response> saveStocks(@RequestBody List<String> stocks) {
        return ResponseEntity.ok(stocksService.saveStocks(stocks));
    }
}
