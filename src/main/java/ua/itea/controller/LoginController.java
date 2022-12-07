package ua.itea.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/login")
public class LoginController {

    @RequestMapping(method = RequestMethod.POST)
    public String login(ModelMap model, @RequestParam String login, @RequestParam String password) {
        String view;
        if ("admin".equals(login) && "password".equals(password)) {
//                login != null && password != null && logi {
            view = "loginsuccess";
            model.addAttribute("authenticatedUser", "Administrator");
        } else {
            view = "loginform";
            model.addAttribute("loginMessage", "Access denied");
        }
        return view;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getLoginForm() {
        return "loginform";
    }

}
