package com.learnjava.completablefuture;

import com.learnjava.domain.Product;
import com.learnjava.service.InventoryService;
import com.learnjava.service.ProductInfoService;
import com.learnjava.service.ReviewService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceUsingCompletableFutureMockTest {

    @Mock
    private ProductInfoService pis;

    @Mock
    private ReviewService rs;

    @Mock
    private InventoryService is;

    @InjectMocks
    ProductServiceUsingCompletableFuture pscf;


    @Test
    void retrieveProductDetailsWithInventory_approach2(){

        String productId = "ABC123";
        when(pis.retrieveProductInfo(any())).thenCallRealMethod();
        when(rs.retrieveReviews(any())).thenThrow(new RuntimeException("Exception occured"));
        when(is.addInventory(any())).thenCallRealMethod();

        Product product = pscf.retrieveProductDetailsWithInventory_approach2(productId);

        assertNotNull(product);
        assertTrue(product.getProductInfo().getProductOptions().size() > 0);
        product.getProductInfo().getProductOptions()
                .forEach(productOption -> {
                    assertNotNull(productOption.getInventory());
                });
        assertNotNull(product.getReview());
        assertEquals(0, product.getReview().getNoOfReviews());

    }

    @Test
    void retrieveProductDetailsWithInventory_productInfoServiceError(){

        String productId = "ABC123";
        when(pis.retrieveProductInfo(any())).thenThrow(new RuntimeException("Exception Occured"));
        when(rs.retrieveReviews(any())).thenCallRealMethod();


        Assertions.assertThrows(RuntimeException.class, () ->
            pscf.retrieveProductDetailsWithInventory_approach2(productId)
        );
    }
}