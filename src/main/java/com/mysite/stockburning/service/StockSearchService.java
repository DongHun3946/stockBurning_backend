package com.mysite.stockburning.service;

import com.mysite.stockburning.entity.StockTickers;
import com.mysite.stockburning.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockSearchService {
    @Autowired
    private StockRepository stockRepository;

    public List<StockTickers> getSuggestions(String ticker) { //추천 검색창 기능
        return stockRepository.findByStockSymbolContaining(ticker);
    }
    public StockTickers getStockTicker(String ticker) {      //해당 티커에 대한 정보 불러오기
        return stockRepository.findByStockSymbol(ticker);
    }

}
