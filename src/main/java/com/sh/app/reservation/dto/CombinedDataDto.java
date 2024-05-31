package com.sh.app.reservation.dto;

import com.sh.app.pay.dto.OrderPayDto;
import com.sh.app.productOrderPay.dto.ProductOrderPayDto;
import com.sh.app.reservationSeat.dto.ReservationSeatDto;
import lombok.Data;

@Data
public class CombinedDataDto {
    private ReservationDto reservationDto;
    private ReservationSeatDto reservationSeatDto;
    private OrderPayDto orderPayDto;
    private ProductOrderPayDto productOrderPayDto;
    private Long storeId;
}
