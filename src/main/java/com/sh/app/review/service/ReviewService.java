package com.sh.app.review.service;

import com.sh.app.review.repository.ReviewRepository;
import org.modelmapper.ModelMapper;
import com.sh.app.review.entity.Review;
import com.sh.app.review.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private ModelMapper modelMapper;
}
