package com.sh.app.productOrderPay.dto;

import com.sh.app.common.Status;
import com.sh.app.productOrderPay.entity.PayStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductOrderPayDto {
    private String id; //order~

    private Long memberId; //회원 아이디[시퀀스]

    private String imp; //가맹점 식별코드

    private String inicis; //이니시스 지원 pg사

    private String amount; //결제 방식

    private Long price; //결제금액

    private Integer count;

    private LocalDateTime payTime;

    private LocalDateTime cancelPayTime;

    private PayStatus payStatus; //상태
}
