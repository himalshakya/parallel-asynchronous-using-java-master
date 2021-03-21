package com.learnjava.thread;

import com.learnjava.domain.Review;
import com.learnjava.service.ReviewService;

public class ReviewServiceRunnable implements Runnable {

    private String productId;
    private ReviewService reviewService;
    private Review review;
    
    public ReviewServiceRunnable(ReviewService reviewService, String productId){
        this.reviewService = reviewService;
        this.productId = productId;
    }

    public Review getReview() {
        return review;
    }

    @Override
    public void run() {
        review = reviewService.retrieveReviews(productId); // blocking call
    }
}
