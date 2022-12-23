package com.komsoft.shopspringmvc.service;

import com.komsoft.shopspringmvc.dto.ProductDto;
import com.komsoft.shopspringmvc.dto.UserDto;
import com.komsoft.shopspringmvc.exception.DataBaseException;
import com.komsoft.shopspringmvc.factory.DAOFactory;
import com.komsoft.shopspringmvc.model.CartModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class CartService {
    private final DAOFactory daoFactory;
    private String CART_MS_BASE_URL = "http://localhost:8080/ShopSpringMVC_war_exploded/cartmicroservice";
    private String CART_MS_BASE_GET_UNPAID_URL = "/getunpaid";
    private String CART_MS_BASE_SAVE_URL = "/save";
//    private final RestTemplate restTemplate;
    private final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

/*
    public CartService(RestTemplateBuilder restTemplate) {
//        this.restTemplate = restTemplate;
        this.restTemplate = restTemplateBuilder.build();
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        messageConverters.add(converter);
        this.restTemplate.setMessageConverters(messageConverters);
    }
*/

    public CartService(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public CartModel create(UserDto user) throws DataBaseException {
        CartModel cart = new CartModel(user.getId());
        return this.saveCart(cart);
    }

    //  Can use External DB operation with cart over Microservice
     public CartModel getUnpaidCartByUser(UserDto user) throws DataBaseException {
        CartModel cart = null;
        cart = daoFactory.getCartDAO().getUnpaidCartByUserId(user.getId());
        return cart;
//  Call to MicroService
/*
         String url = CART_MS_BASE_URL + CART_MS_BASE_GET_UNPAID_URL;
     logger.info("[CartService.getUnpaidCartByUser] called to Microservice by url={}", url);
         HttpHeaders headers = new HttpHeaders();
         headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//         headers.setContentType(MediaType.APPLICATION_JSON);
         MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
         map.add("id", String.valueOf(user.getId()));
         RestTemplate restTemplate = new RestTemplate();
         HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
         ResponseEntity<CartModel> response = restTemplate.postForEntity(url, request, CartModel.class);
logger.info("[CartService.getUnpaidCartByUser] got response: {}", response.getStatusCode());
     logger.info("[CartService.getUnpaidCartByUser] got response: {}", response.getBody());
         if (response.getStatusCode() == HttpStatus.FOUND) {
//  TODO           response.body();
             cart = response.getBody();
             return cart;
         } else {
             logger.info("[CartService.getUnpaidCartByUser] got response: {}", response.getStatusCode().getReasonPhrase());
             throw new DataBaseException(String.format("Database error %s. No Cart for user %s found",
                     response.getStatusCode(), user.getFullName()));
         }
//                 log.error("For Order ID: {}, error response: {} is received to create Order in Customer Microservice",
//                         order.getId(),
//                         responseEntity.getStatusCode().getReasonPhrase());
//                 throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, String.format("For Order UUID: %s, Customer Microservice Message: %s", order.getId(), responseEntity.getStatusCodeValue()));
//             }
//             if (responseEntity.hasBody()) {
//                 log.error("Order From Response: {}", responseEntity.getBody().getId());
//             }
//             return cart;
*/
     }





    //  Can use External DB operation with cart over Microservice
    public CartModel saveCart(CartModel cart) throws DataBaseException {
        return daoFactory.getCartDAO().save(cart);
//  Call to MicroService
/*
        final String url = CART_MS_BASE_URL + CART_MS_BASE_SAVE_URL;
        logger.info("[CartService.create] called to Microservice by url={}", url);
        try {
*/
/*
            RestTemplate restTemplate = new RestTemplate();

            HttpEntity<Foo> request = new HttpEntity<>(new Foo("bar"));
            Foo foo = restTemplate.postForObject(fooResourceUrl, request, Foo.class);
            Assertions.assertNotNull(foo);
            Assertions.assertEquals(foo.getName(), "bar");
*//*


            return cart;
        } catch (Exception e) {
            logger.error("[CartService.create] Exception {}", e.getMessage());
            e.printStackTrace();
            throw new DataBaseException(e.getMessage());
        }
*/
    }




//    теж саме, що й записати, але нічого не повертає. Просто, щоб завершити роботу з картою
    public void closeCart(CartModel cart) {
        try {
            this.saveCart(cart);
        } catch (DataBaseException e) {
            e.printStackTrace();    // хай так для прикладу
        }
    }

    public CartModel addProduct(CartModel cart, ProductDto productDto, int count) throws DataBaseException {
        if (productDto != null) {
            cart.addProduct(productDto, count);
            cart = this.saveCart(cart);
        }
        return cart;
    }

    public CartModel deleteProduct(CartModel cart, ProductDto productDto) throws DataBaseException {
        if (productDto != null) {
            cart.removeProduct(productDto);
            cart = this.saveCart(cart);
        }
        return cart;
    }

/*
            try {
                final var request = new HttpEntity<>(order, headers);
                final var responseEntity = restTemplate.postForEntity(url, request, Order.class);
                if (responseEntity.getStatusCode().isError()) {
                    log.error("For Order ID: {}, error response: {} is received to create Order in Customer Microservice",
                            order.getId(),
                            responseEntity.getStatusCode().getReasonPhrase());
                    throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, String.format("For Order UUID: %s, Customer Microservice Message: %s", order.getId(), responseEntity.getStatusCodeValue()));
                }
                if (responseEntity.hasBody()) {
                    log.error("Order From Response: {}", responseEntity.getBody().getId());
                }
            }
*/

}
