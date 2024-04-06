package com.sh.app.store.controller;

import com.sh.app.store.dto.StoreDetailDto;
import com.sh.app.store.service.StoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/store")
public class StoreController {
    @Autowired
    private StoreService storeService;

    @GetMapping("/storeList.do")
    public void storeList(Model model) {
        List<StoreDetailDto> storeDetailDtos = storeService.findAll();

        log.debug("store = {}", storeDetailDtos);
    }
}
