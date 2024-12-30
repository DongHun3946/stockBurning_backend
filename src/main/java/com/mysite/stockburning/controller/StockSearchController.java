package com.mysite.stockburning.controller;

import com.mysite.stockburning.entity.StockTickers;
import com.mysite.stockburning.service.StockApiService;
import com.mysite.stockburning.service.StockSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stock")
public class StockSearchController {
    @Autowired
    private StockSearchService stockSearchService;
    @GetMapping("/suggestions") //http://localhost:8080/stock/suggestions?prefix=AA
    public List<StockTickers> getStockSuggestions(@RequestParam("prefix") String prefix){
        return stockSearchService.getSuggestions(prefix);
    }
    @GetMapping             //http://localhost:8080/stock?ticker=AAPL
    public StockTickers getStockByTicker(@RequestParam("ticker") String ticker){
        return stockSearchService.getStockTicker(ticker);
    }
}
