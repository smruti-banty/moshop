package org.smruti.moshop.service;

import java.util.List;

import org.smruti.moshop.model.Stock;
import org.smruti.moshop.repository.StockRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StockService {
    private final StockRepository stockRepository;

    public void createOrUpdate(Stock stock) {
        stockRepository.save(stock);
    }

    public List<Stock> getStocks() {
        return stockRepository.findAll();
    }
}
