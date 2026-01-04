package com.example.jewelry.service;

import com.example.jewelry.model.Response;
import com.example.jewelry.model.Stocks;
import com.example.jewelry.repository.StockRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class StocksService {
    @Autowired
    StockRepository stockRepository;

    public Response saveStocks(List<String> newStocks) {
        Stocks stock = stockRepository.findById(1L).orElse(new Stocks());
        stock.setId(1L);
        Set<String> stocksSet = stock.getItems() == null ? new HashSet<>() : stock.getItems();
        stocksSet.addAll(newStocks);
        stock.setItems(stocksSet);
        stockRepository.save(stock);
        log.info("Stocks saved/updated successfully : {}", stock.getItems());
        return new Response(newStocks, "Stock saved successfully");
    }

    public void saveStocks(List<String> newStocks, List<String> hsnCodes) {
        Stocks stock = stockRepository.findById(1L).orElse(new Stocks());
        stock.setId(1L);
        Set<String> stocksSet = stock.getItems() == null ? new HashSet<>() : stock.getItems();
        Set<String> hsns = stock.getHsnCodes() == null ? new HashSet<>() : stock.getHsnCodes();
        stocksSet.addAll(newStocks);
        hsns.addAll(hsnCodes);
        stock.setItems(stocksSet);
        stock.setHsnCodes(hsns);
        stockRepository.save(stock);
        log.info("Stocks saved/updated successfully : {}", stock.getItems());
        log.info("Hsn codes saved/updated successfully : {}", stock.getHsnCodes());
    }
    public Response fetchAllStocks() {
        Stocks stocks = new Stocks();
        if (stockRepository.existsById(1L)) {
            stocks = stockRepository.findById(1L).get();
            log.info("Fetched stocks successfully : {}", stocks);
        }
        return new Response(stocks, "Stocks fetched successfully");
    }
}
