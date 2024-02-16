package com.sh.app.admin.service;

import com.sh.app.admin.entity.Admin;
import com.sh.app.admin.repository.AdminRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;
    public Admin findByUsername(String username) {
        return adminRepository.findByUsername(username);
    }
}
