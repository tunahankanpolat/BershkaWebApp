package pure.bershka.apiControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pure.bershka.business.abstracts.ProductViewService;
import pure.bershka.core.utilities.result.DataResult;
import pure.bershka.entities.concretes.ProductView;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/product-view")
public class ProductViewController {
    ProductViewService productViewService;

    @Autowired
    public ProductViewController(ProductViewService productViewService) {
        this.productViewService = productViewService;
    }

    @GetMapping("/get-all")
    public DataResult<List<ProductView>> getAll() {
        return this.productViewService.getAll();
    }
}
