package pure.bershka.apiControllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pure.bershka.business.abstracts.StockService;
import pure.bershka.core.utilities.result.DataResult;
import pure.bershka.core.utilities.result.Result;
import pure.bershka.core.utilities.result.SuccessDataResult;
import pure.bershka.entities.concretes.Stock;

@CrossOrigin
@RestController
@RequestMapping("/api/stock")
public class StockController {
    private StockService stockService;

    @Autowired
    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @PostMapping("/add")
    public Result add(@RequestBody Stock stock){
        return this.stockService.addStock(stock);
    }
    @PutMapping("/update")
    public Result update(int stockId, @RequestBody Stock stock){
        return this.stockService.updateStock(stockId,stock);
    }
    @DeleteMapping("/delete")
    public Result delete(int stockId){
        return this.stockService.deleteStock(stockId);
    }
    
	@GetMapping("/get-product-with-id")
	public DataResult<List<Stock>> getProductSizes(int productId) {
		return this.stockService.getProductSizes(productId); 
	}
}
