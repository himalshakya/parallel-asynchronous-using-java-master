package com.learnjava.thread;

import com.learnjava.domain.Product;
import com.learnjava.domain.ProductInfo;
import com.learnjava.domain.Review;
import com.learnjava.service.ProductInfoService;
import com.learnjava.service.ReviewService;

import static com.learnjava.util.CommonUtil.stopWatch;
import static com.learnjava.util.LoggerUtil.log;

public class ProductInfoServiceThread {
    private ProductInfoService productInfoService;
    private ReviewService reviewService;

    public ProductInfoServiceThread(ProductInfoService productInfoService, ReviewService reviewService) {
        this.productInfoService = productInfoService;
        this.reviewService = reviewService;
    }

    public Product retrieveProductDetails(String productId) throws InterruptedException {
        stopWatch.start();

        ProductInfoRunnable productInfoRunnable = new ProductInfoRunnable(productInfoService, productId);
        Thread productInfoThread = new Thread(productInfoRunnable);

        ReviewServiceRunnable reviewServiceRunnable = new ReviewServiceRunnable(reviewService, productId);
        Thread reviewServiceThread = new Thread(reviewServiceRunnable);

        productInfoThread.start();
        reviewServiceThread.start();

        productInfoThread.join();
        reviewServiceThread.join();

        ProductInfo productInfo = productInfoRunnable.getProductInfo();
        Review review = reviewServiceRunnable.getReview();

        stopWatch.stop();
        log("Total Time Taken : "+ stopWatch.getTime());
        return new Product(productId, productInfo, review);
    }

    public static void main(String[] args) throws InterruptedException {

        ProductInfoService productInfoService = new ProductInfoService();
        ReviewService reviewService = new ReviewService();
        ProductInfoServiceThread productService = new ProductInfoServiceThread(productInfoService, reviewService);
        String productId = "ABC123";
        Product product = productService.retrieveProductDetails(productId);
        log("Product is " + product);

    }
}
