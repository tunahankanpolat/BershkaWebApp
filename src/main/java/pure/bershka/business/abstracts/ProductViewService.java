package pure.bershka.business.abstracts;

import pure.bershka.core.utilities.result.DataResult;
import pure.bershka.entities.concretes.ProductView;

import java.util.List;

public interface ProductViewService {
    DataResult<List<ProductView>> getAll();
}
