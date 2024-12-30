package com.mysite.stockburning.service;

import com.mysite.stockburning.dto.QQQIndexDTO;
import com.mysite.stockburning.dto.SPYIndexDTO;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class StockApiService {
    private final String apiKey = "ctkkv0hr01qntkqpd110ctkkv0hr01qntkqpd11g";
    private QQQIndexDTO cachedQQQData = null;
    private SPYIndexDTO cachedSPYData = null;

    //2분마다 API를 호출하여 가격정보를 cachedStockData에 저장
    @Scheduled(fixedRate = 120000)
    public void fetchStockData(){
        try{
            RestTemplate restTemplate1 = new RestTemplate(); //HTTP 요청을 보내고 응답을 처리하기 위한 클래스
            String url1 = "https://finnhub.io/api/v1/quote?symbol=QQQ&token=" + apiKey;
            this.cachedQQQData = restTemplate1.getForObject(url1, QQQIndexDTO.class); //HTTP GET 요청을 보내고 응답을 받아서 StockIndexDTO 객체로 변환한 후 반환

            RestTemplate restTemplate2 = new RestTemplate(); //HTTP 요청을 보내고 응답을 처리하기 위한 클래스
            String url2 = "https://finnhub.io/api/v1/quote?symbol=SPY&token=" + apiKey;
            this.cachedSPYData = restTemplate2.getForObject(url2, SPYIndexDTO.class); //HTTP GET 요청을 보내고 응답을 받아서 StockIndexDTO 객체로 변환한 후 반환
        }catch(Exception e){
            System.out.println("지수 데이터를 가져오는데 실패하였습니다." + e.getMessage());
        }
    }
    //value = 캐시이름, key = 캐시 키, unless = 반환값이 널일 경우 캐시하지 않도록 함
    @Cacheable(value="stockCacheQQQ", key="'stockIndexQQQ'", unless="#result==null" )
    public QQQIndexDTO getCachedQQQData(){
        return cachedQQQData;
    }
    @Cacheable(value="stockCacheSPY", key="'stockIndexSPY'", unless="#result==null" )
    public SPYIndexDTO getCachedSPYData(){
        return cachedSPYData;
    }
}
/*
  2분마다 해당 url에서 API를 호출하여 cachedStockData에 저장
  클라이언트가 서버에 /api/stock 으로 HTTP 요청을 보내면 cachedStockData 값을 반환
*/
