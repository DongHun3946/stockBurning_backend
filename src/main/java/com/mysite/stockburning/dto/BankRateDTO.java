package com.mysite.stockburning.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class BankRateDTO {
    private String country;
    private String bankTicker;
    private String currentRate;
    private String nextMeeting;
}
