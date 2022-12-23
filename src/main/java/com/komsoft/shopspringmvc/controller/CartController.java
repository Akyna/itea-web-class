package com.komsoft.shopspringmvc.controller;

import com.komsoft.shopspringmvc.dto.ProductDto;
import com.komsoft.shopspringmvc.dto.UserDto;
import com.komsoft.shopspringmvc.exception.DataBaseException;
import com.komsoft.shopspringmvc.exception.ValidationException;
import com.komsoft.shopspringmvc.model.CartModel;
import com.komsoft.shopspringmvc.service.CartService;
import com.komsoft.shopspringmvc.service.ProductService;
import com.komsoft.shopspringmvc.util.Header;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/cart")
//@RequestMapping("/cartmicroservice")
public class CartController {

    private final CartService cartService;
    private final ProductService productService;

    public CartController(CartService cartService, ProductService productService) {
        this.cartService = cartService;
        this.productService = productService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/demo")
    public String  demo( ) {
        System.out.println("from Demo");
        return Header.HOME_PAGE;
    }

        @RequestMapping(method = RequestMethod.POST, value = "/add")
    public String addProduct( HttpServletRequest request, HttpSession session,
                             @RequestParam(name = "id") String productId, @RequestParam(name = "count") String productQuantity) {
        String view = "redirect:" + request.getHeader("referer");
        if (session.getAttribute(Header.AUTHENTICATED_USER_KEY) == null) {
            return Header.INFO_PAGE;
        } else {
            try {
                int count = productQuantity == null ? 0 : Integer.parseInt(productQuantity);
                UserDto user = (UserDto) session.getAttribute(Header.AUTHENTICATED_USER_KEY);
                CartModel cart = (CartModel) session.getAttribute(Header.USER_CART);
                if (cart == null) {
                    cart = cartService.create(user);
                }
                ProductDto product = productService.getById(productId);
                cart = cartService.addProduct(cart, product, count);
                session.setAttribute(Header.USER_CART, cart);
                return Header.CART_PAGE;
//                view = "redirect:" + request.getHeader("referer");
            } catch (NumberFormatException ignored) {
//            because  <input type="number"
                return "";
            } catch (ValidationException | DataBaseException e) {
                return Header.ERROR_PAGE;
//                view = Header.ERROR_PAGE;
            }
        }
    }
/*
    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public String addProduct(ModelMap modelMap, HttpServletRequest request, HttpSession session,
                             @RequestParam(name = "id") String productId, @RequestParam(name = "count") String productQuantity) {
        String view = "redirect:" + request.getHeader("referer");
        if (session.getAttribute(Header.AUTHENTICATED_USER_KEY) == null) {
            view = Header.INFO_PAGE;
            modelMap.addAttribute(Header.MESSAGE, "To put items into Cart please login first");
        } else {
            try {
                int count = productQuantity == null ? 0 : Integer.parseInt(productQuantity);
                UserDto user = (UserDto) session.getAttribute(Header.AUTHENTICATED_USER_KEY);
                CartModel cart = (CartModel) session.getAttribute(Header.USER_CART);
                if (cart == null) {
                    cart = cartService.create(user);
                }
                ProductDto product = productService.getById(productId);
                cart = cartService.addProduct(cart, product, count);
                session.setAttribute(Header.USER_CART, cart);
                view = "redirect:" + request.getHeader("referer");
            } catch (NumberFormatException ignored) {
//            because  <input type="number"
            } catch (ValidationException | DataBaseException e) {
                view = Header.ERROR_PAGE;
                modelMap.addAttribute(Header.MESSAGE, e.getMessage());
            }
        }
        return view;
    }
*/

    @RequestMapping(method = RequestMethod.POST, value = "/delete")
    public String deleteProduct(ModelMap modelMap, HttpSession session,
                                @RequestParam(name = "id") String productId) {
        String view;
        try {
            CartModel cart = (CartModel) session.getAttribute(Header.USER_CART);
            ProductDto product = productService.getById(productId);
            cart = cartService.deleteProduct(cart, product);
            session.setAttribute(Header.USER_CART, cart);
            view = Header.CART_PAGE;
        } catch (ValidationException | DataBaseException e) {
            view = Header.ERROR_PAGE;
            modelMap.addAttribute(Header.MESSAGE, e.getMessage());
        }
        return view;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String showCart(ModelMap modelMap, HttpSession session) {
        String view;
        UserDto user = (UserDto) session.getAttribute(Header.AUTHENTICATED_USER_KEY);
        if (user == null) {
            view = Header.INFO_PAGE;
            modelMap.addAttribute(Header.MESSAGE, "To show Cart or put product in, please login first.");
        } else {
            CartModel cart = (CartModel) session.getAttribute(Header.USER_CART);
            if (cart == null || cart.size() == 0) {
                view = Header.INFO_PAGE;
                modelMap.addAttribute(Header.MESSAGE, "Your Cart is empty. First put items in if you are logged in.");
            } else {
                view = Header.CART_PAGE;
            }
        }
        return view;
    }

}
