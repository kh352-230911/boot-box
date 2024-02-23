package com.sh.app.ask.service;

import com.sh.app.ask.dto.CreateAskDto;
import com.sh.app.ask.entity.Ask;
import com.sh.app.ask.repository.AskRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public void createAsk(CreateAskDto createAskDto) {
        Ask ask = askRepository.save(convertToAsk(createAskDto));

    }

    private Ask convertToAsk(CreateAskDto createAskDto) {
        return modelMapper.map(createAskDto, Ask.class);
    }

    public Ask findById(Long id) {
        return askRepository.findById(id).orElseThrow();
    }
}
