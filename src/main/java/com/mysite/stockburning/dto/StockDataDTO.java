package com.mysite.stockburning.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class StockDataDTO {
    private Long id;
    private String stockSymbol;
    private String volume;
    private String avgVolume;
    private String rsi;
}
