package com.mysite.stockburning.service;

import com.mysite.stockburning.dto.BankRateDTO;
import com.mysite.stockburning.dto.StockDataDTO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class WebScrapingService {
    public List<BankRateDTO> bankRates() throws IOException {
        String url = "https://kr.investing.com/central-banks/";
        Document document = Jsoup.connect(url).get();

        Element table = document.getElementById("curr_table"); //id = "curr_table"
        Elements rows = table.select("tbody tr"); //<tbody>-<tr>

        List<BankRateDTO> bankRateList = new ArrayList<>();       //DTO 리스트 생성
        for (Element row : rows) {
            String country = row.select("td.bold.left.noWrap a").text();       // 국가 이름
            String bankTicker = row.select("td.bold.left.noWrap span").text(); //중앙은행 티커
            String currentRate = row.select("td").get(2).text();               // 현재 금리
            String nextMeeting = row.select("td").get(3).text();              // 다음 회의

            bankTicker = bankTicker.replaceAll("[()]", "");
            BankRateDTO dto = new BankRateDTO(country, bankTicker, currentRate, nextMeeting);
            bankRateList.add(dto);
        }
        return bankRateList;
    }

    public StockDataDTO scrapeStockData(StockDataDTO dataDTO) throws IOException {
        String url = "https://finviz.com/quote.ashx?t=" + dataDTO.getStockSymbol() + "&p=d";
        Document document = Jsoup.connect(url).get();

        Element table = document.select("table.js-snapshot-table.snapshot-table2.screener_snapshot-table-body").first();
        if (table != null) {
            Elements rows = table.select("tbody tr");
            Element Row_9 = rows.get(8);                             // 9번째 tr 선택
            Element rsi_td = Row_9.select("td").get(9);   // 10번째 td 선택
            Element rsi = rsi_td.select("b").first();    //span 태그 선택

            Element Row_12 = rows.get(11);
            Element avgVolume_td = Row_12.select("td").get(9);
            Element avgVolume = avgVolume_td.select("b").first();

            Element Row_13 = rows.get(12);
            Element volume_td = Row_13.select("td").get(9);
            Element volume = volume_td.select("b").first();

            if (rsi != null) {
                dataDTO.setRsi(rsi.text());
            }
            if (avgVolume != null) {
                dataDTO.setAvgVolume(avgVolume.text());
            }
            if (volume != null) {
                dataDTO.setVolume(volume.text());
            }
        }
        return dataDTO;
    }
}
