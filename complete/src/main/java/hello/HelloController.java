package hello;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class HelloController {	

	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
	@PostMapping("/")
	public String search(@RequestParam(value="market", defaultValue="") String[] market,
			@RequestParam(value="stack", defaultValue="") String[] stack, Model model) {
		model.addAttribute("market", market);
		model.addAttribute("stack", stack);
		model.addAttribute("products", Product.parseJson(RestService.call("/product", market, stack)));
		   
		return "index";
	}
}