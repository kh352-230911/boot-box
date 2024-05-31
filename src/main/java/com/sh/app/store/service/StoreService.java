package com.sh.app.store.service;

import com.sh.app.pay.dto.OrderPayDto;
import com.sh.app.productBuy.entity.ProductBuy;
import com.sh.app.productBuy.repository.ProductBuyRepository;
import com.sh.app.productOrderPay.dto.ProductOrderPayDto;
import com.sh.app.productOrderPay.entity.ProductOrderPay;
import com.sh.app.productOrderPay.repository.ProductOrderPayRepository;
import com.sh.app.store.dto.StoreDetailDto;
import com.sh.app.store.entity.Store;
import com.sh.app.store.repository.StoreRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Slf4j
public class StoreService {
    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private ProductOrderPayRepository productOrderPayRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProductBuyRepository productBuyRepository;

    public List<StoreDetailDto> findAll() {
        List<Store> stores = storeRepository.findAll();
        List<StoreDetailDto> storeDetailDtos = new ArrayList<>();

        for (Store store : stores) {
            StoreDetailDto storeDetailDto = convertToStoreDetailDto(store);

            storeDetailDtos.add(storeDetailDto);
        }

        return storeDetailDtos;
    }

    private StoreDetailDto convertToStoreDetailDto(Store store) {
        return modelMapper.map(store, StoreDetailDto.class);
    }

    public ProductOrderPayDto insertProductOrderPay(ProductOrderPayDto productOrderPayDto, Long storeId) {
        // ProductOrderPayDto를 ProductOrderPay 엔터티로 변환
        ProductOrderPay productOrderPay = convertToProductOrderPay(productOrderPayDto);

        // Store 조회
        Store store = storeRepository.findById(storeId).orElseThrow(() -> new EntityNotFoundException("Store not found with ID: " + storeId));

        // ProductOrderPay 저장
        productOrderPay = productOrderPayRepository.save(productOrderPay);

        // ProductBuy 객체 생성 및 설정
        ProductBuy productBuy = new ProductBuy();
        productBuy.setProductOrderPay(productOrderPay);
        productBuy.setStore(store);

        // ProductBuy 저장
        productBuy = productBuyRepository.save(productBuy);

        // ProductOrderPayDto로 변환하여 반환
        return convertToProductOrderPayDto(productOrderPay);
    }

    private ProductOrderPayDto convertToProductOrderPayDto(ProductOrderPay productOrderPay) {
        return modelMapper.map(productOrderPay, ProductOrderPayDto.class);
    }

    private ProductOrderPay convertToProductOrderPay(ProductOrderPayDto productOrderPayDto) {
        return modelMapper.map(productOrderPayDto, ProductOrderPay.class);
    }
}
