package com.learnjava.thread;

import com.learnjava.domain.ProductInfo;
import com.learnjava.service.ProductInfoService;

public class ProductInfoRunnable implements Runnable {

    private ProductInfo productInfo;
    private String productId;
    private ProductInfoService productInfoService;

    public ProductInfoRunnable(ProductInfoService productInfoService, String productId) {
        this.productInfoService = productInfoService;
        this.productId = productId;
    }

    public ProductInfo getProductInfo() {
        return productInfo;
    }

    @Override
    public void run() {
        productInfo = this.productInfoService.retrieveProductInfo(this.productId);
    }

}
