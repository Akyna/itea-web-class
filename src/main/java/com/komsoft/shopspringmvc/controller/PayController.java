package com.komsoft.shopspringmvc.controller;

import com.komsoft.shopspringmvc.dto.UserDto;
import com.komsoft.shopspringmvc.model.CartModel;
import com.komsoft.shopspringmvc.service.CartService;
import com.komsoft.shopspringmvc.util.Header;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/pay")
public class PayController {

    private final CartService cartService;

    public PayController(CartService cartService) {
        this.cartService = cartService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String doPay(ModelMap modelMap) {
        String view = Header.PAY_PAGE;
        modelMap.addAttribute(Header.PAY_MESSAGE, "You can pay your items on this page");
        return view;
    }

    @RequestMapping(value = "/success", method = RequestMethod.POST)
    public String closeCart(ModelMap modelMap, HttpSession session) {
        CartModel cart = (CartModel) session.getAttribute(Header.USER_CART);
        if (cart != null) {
            cart.setPaid(true);
            cartService.closeCart(cart);
            session.removeAttribute(Header.USER_CART);
        }
        String view = Header.PAY_SUCCESS_PAGE;
        modelMap.addAttribute(Header.PAY_MESSAGE, "Pay successfully. Your cart is closed. Keep visiting!");
        return view;
    }

}
