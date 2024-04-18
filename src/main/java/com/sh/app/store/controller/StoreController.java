package com.sh.app.store.controller;

import com.sh.app.auth.vo.MemberDetails;
import com.sh.app.pay.dto.OrderPayDto;
import com.sh.app.productOrderPay.dto.ProductOrderPayDto;
import com.sh.app.productOrderPay.entity.ProductOrderPay;
import com.sh.app.reservation.dto.CombinedDataDto;
import com.sh.app.reservation.dto.ReservationDto;
import com.sh.app.reservation.entity.Reservation;
import com.sh.app.reservationSeat.dto.ReservationSeatDto;
import com.sh.app.reservationSeat.dto.ReservationSeatDto2;
import com.sh.app.store.dto.StoreDetailDto;
import com.sh.app.store.service.StoreService;
import com.siot.IamportRestClient.IamportClient;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/store")
public class StoreController {
    @Autowired
    private StoreService storeService;

    private IamportClient iamportClient;

    @Value("${imp.api.key}")
    private String apiKey;

    @Value("${imp.api.secretkey}")
    private String secretKey;

    @PostConstruct
    public void init() {
        this.iamportClient = new IamportClient(apiKey, secretKey);
    }

    @GetMapping("/storeList.do")
    public void storeList(Model model) {
        List<StoreDetailDto> storeDetailDtos = storeService.findAll();

//        log.debug("store = {}", storeDetailDtos);

        model.addAttribute("stores", storeDetailDtos);
    }

    @PostMapping("/storePayment")
    public ResponseEntity<?> reservationStart(@AuthenticationPrincipal MemberDetails memberDetails,
                                              @RequestBody CombinedDataDto combinedDataDto) throws IOException {

        //3번 저장 후 return
        try {
            //결제건 저장하기==============================================================================================================
            ProductOrderPayDto productOrderPayDto = combinedDataDto.getProductOrderPayDto();
            System.out.println(productOrderPayDto);
            productOrderPayDto.setMemberId(memberDetails.getMember().getId());
            Long storeId = combinedDataDto.getStoreId();
            //정보 세팅 후 db저장하기
            productOrderPayDto = storeService.insertProductOrderPay(productOrderPayDto, storeId);
            log.debug("productOrderPayDto = {}", productOrderPayDto);

            // 모든 저장이 성공한 경우 성공 응답을 보냄
            return ResponseEntity.ok().body("All entities saved successfully :)");
        } catch (Exception e) {
            // 어떤 저장 작업이라도 실패한 경우 예외 처리
            e.printStackTrace();
            // 실패 응답을 보낼 수 있음
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save datas....");
        }
    }
}
