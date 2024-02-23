package com.sh.app.ask.service;

import com.sh.app.ask.dto.AskDetailDto;
import com.sh.app.ask.dto.CreateAskDto;
import com.sh.app.ask.entity.Ask;
import com.sh.app.ask.repository.AskRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class AskService {

    @Autowired
    private AskRepository askRepository;
    @Autowired
    private ModelMapper modelMapper;

    public List<Ask> findAll() {
        System.out.println("문의조회 service");
        return askRepository.findAll();
    }

    public AskDetailDto findById(Long id) {
        return askRepository.findById(id)
                .map((ask) -> convertToAskDetailDto(ask))
                .orElseThrow();
    }

    private AskDetailDto convertToAskDetailDto(Ask ask) {
        AskDetailDto askDetailDto = modelMapper.map(ask, AskDetailDto.class);
        askDetailDto.setCreatedAt(LocalDate.now());
        return askDetailDto;
    }

    public void createAsk(CreateAskDto createAskDto) {
        Ask ask = askRepository.save(convertToAsk(createAskDto));

    }

    private Ask convertToAsk(CreateAskDto createAskDto) {
        return modelMapper.map(createAskDto, Ask.class);
    }
}
