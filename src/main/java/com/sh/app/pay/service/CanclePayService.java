package com.sh.app.pay.service;


import com.sh.app.pay.repository.CanclePayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional //기존 mvc패턴 처럼 rollback 처리. class 단에서 선언하면 하위 모든 메소드에도 모두 어노테이션 처리됨
public class CanclePayService {

    @Autowired
    CanclePayRepository canclePayRepository;

}
