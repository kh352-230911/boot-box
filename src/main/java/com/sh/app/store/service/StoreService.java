package com.sh.app.store.service;

import com.sh.app.store.dto.StoreDetailDto;
import com.sh.app.store.entity.Store;
import com.sh.app.store.repository.StoreRepository;
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
    private ModelMapper modelMapper;

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
}
