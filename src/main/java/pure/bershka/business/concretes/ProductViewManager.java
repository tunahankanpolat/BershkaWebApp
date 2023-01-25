package pure.bershka.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pure.bershka.business.abstracts.ProductViewService;
import pure.bershka.core.utilities.result.DataResult;
import pure.bershka.core.utilities.result.SuccessDataResult;
import pure.bershka.dataAccess.abstracts.ProductViewDao;
import pure.bershka.entities.concretes.Product;
import pure.bershka.entities.concretes.ProductView;

import java.util.List;

@Service
public class ProductViewManager implements ProductViewService {
    ProductViewDao productViewDao;

    @Autowired
    public ProductViewManager(ProductViewDao productViewDao) {
        this.productViewDao = productViewDao;
    }

    @Override
    public DataResult<List<ProductView>> getAll() {
        return new SuccessDataResult<List<ProductView>>(this.productViewDao.findAll(), "Data Listelendi");
    }
}
