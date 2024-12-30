package com.mysite.stockburning.dto;

import lombok.*;

import java.io.Serializable;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class SPYIndexDTO implements Serializable {
    private double c;  //현재 주가
    private double pc; //전날 종가
}
