package hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @RequestMapping("/product")
    public Product[] product(@RequestParam(value="market", defaultValue="") String[] market,
    		@RequestParam(value="stack", defaultValue="") String[] stack) {
        return Product.find(market, stack);
    }
}
