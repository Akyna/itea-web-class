package com.komsoft.shopspringmvc.controller;

import com.komsoft.shopspringmvc.exception.DataBaseException;
import com.komsoft.shopspringmvc.model.CartModel;
import com.komsoft.shopspringmvc.service.CartMicroServiceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.lang.invoke.MethodHandles;

@RestController
@RequestMapping("/cartmicroservice")
public class CartMicroServiceController {

    private final CartMicroServiceService cartService;

    Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public CartMicroServiceController(CartMicroServiceService cartService) {
        this.cartService = cartService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/getunpaid")
    public ResponseEntity<CartModel> getUnpaid(@RequestParam(name = "id") String userId) {
        logger.info("[CartMicroServiceController.getUnpaid] called, userId={}", userId);
        try {
            if (userId == null || userId.isEmpty()) {
                logger.info("[CartMicroServiceController.getUnpaid] badRequest");
                return ResponseEntity.badRequest().build();
            }
            long id = Long.parseLong(userId);
            CartModel cart = cartService.getUnpaidCartByUser(id);
//            return ResponseEntity.status(HttpStatus.ACCEPTED).body(cart);
            if (cart != null) {
                logger.info("[CartMicroServiceController.getUnpaid] Found Cart: {}", cart);
                return ResponseEntity.status(HttpStatus.FOUND).body(cart);
            } else {
                logger.info("[CartMicroServiceController.getUnpaid] notFound");
                return ResponseEntity.notFound().build();
            }
//            return ResponseEntity.ok().body(cart);
        } catch (DataBaseException e) {
            logger.info("[CartMicroServiceController.getUnpaid] DataBaseException");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/save")
    public ResponseEntity<CartModel> saveCart(@Validated @RequestBody CartModel cart) {
        logger.info("[saveCart] called, cart={}", cart);
        try {
            CartModel savedCart = cartService.saveCart(cart);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(savedCart);
        } catch (DataBaseException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
