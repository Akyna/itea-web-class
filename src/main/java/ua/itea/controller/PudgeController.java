package ua.itea.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.itea.model.Pudge;

@Controller
@RequestMapping("/pudge")
public class PudgeController {

    @RequestMapping(method = RequestMethod.GET, value = "/get")
    public String getPudge(ModelMap model) {
        Pudge pudge = new Pudge("Pudge", 11);
        model.addAttribute("pudgeObject", pudge);
        return "pudgeView";
    }

//    @RequestMapping(method = RequestMethod.POST, value="/post")



}
