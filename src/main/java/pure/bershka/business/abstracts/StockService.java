package pure.bershka.business.abstracts;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;

import pure.bershka.core.utilities.result.DataResult;
import pure.bershka.core.utilities.result.Result;
import pure.bershka.entities.concretes.Size;
import pure.bershka.entities.concretes.Stock;

public interface StockService {

    Result addStock(Stock stock);
    Result updateStock(int stockId, Stock stock);
    Result deleteStock(int stockId);
    DataResult<List<Stock>> getProductSizes(int productId);


}
