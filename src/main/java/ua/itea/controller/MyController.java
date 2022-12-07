package ua.itea.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/hellopage")
public class MyController {

	@RequestMapping(value="/hi", method=RequestMethod.GET)
	public String printHello(ModelMap modelMap) {
		modelMap.addAttribute("msg", "Hello from Spring MVC");
		return "view";
	}
}
