package pure.bershka.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pure.bershka.business.abstracts.StockService;
import pure.bershka.core.utilities.result.DataResult;
import pure.bershka.core.utilities.result.Result;
import pure.bershka.core.utilities.result.SuccessDataResult;
import pure.bershka.core.utilities.result.SuccessResult;
import pure.bershka.dataAccess.abstracts.StockDao;
import pure.bershka.entities.concretes.Stock;

@Service
public class StockManager implements StockService {
    private StockDao stockDao;
    @Autowired
    public StockManager(StockDao stockDao) {
        this.stockDao = stockDao;
    }

    // FOR ADMIN
    @Override
    public Result addStock(Stock stock) {
        this.stockDao.save(stock);
        return new SuccessResult("true");
    }

    @Override
    public Result updateStock(int stockId, Stock stock) {
        Stock updatedStock = this.stockDao.findById(stockId).get();
        updatedStock.setStock(stock);
        this.stockDao.save(updatedStock);
        return new SuccessResult("true");
    }

    @Override
    public Result deleteStock(int stockId) {
        Stock deletedStock = this.stockDao.findById(stockId).get();
        this.stockDao.delete(deletedStock);
        return new SuccessResult("true");
    }

	@Override
	public DataResult<List<Stock>> getProductSizes(int productId) {
		return new SuccessDataResult<List<Stock>>(this.stockDao.getProductSizes(productId)); 
	}

	
}
