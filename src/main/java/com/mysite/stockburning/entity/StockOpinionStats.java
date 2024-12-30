package com.mysite.stockburning.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class StockOpinionStats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stockTickers_id", nullable = false) //name은 왜래키 이름 설정
    private StockTickers stockTickers;

    @Column(nullable = false)
    private int bullishCnt = 0;

    @Column(nullable = false)
    private int bearishCnt = 0;

    @Column(nullable = false)
    private LocalDateTime date;

    @Column(nullable = false, updatable = false)
    private LocalDateTime updatedAt = LocalDateTime.now();
}

/*
CREATE TABLE StockOpinionStats (
    id INT AUTO_INCREMENT PRIMARY KEY,               -- 고유 ID
    stock_symbol VARCHAR(10) NOT NULL,               -- 주식 티커
    bullish_count INT DEFAULT 0,                     -- 상승 의견 게시글 수
    bearish_count INT DEFAULT 0,                     -- 하락 의견 게시글 수
    date DATE NOT NULL,                              -- 집계 날짜 (1일 단위)
    last_updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, -- 마지막 갱신일
    UNIQUE(stock_symbol, date)                      -- 주식 티커와 날짜별로 유일한 값
);
*/