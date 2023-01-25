package pure.bershka.business.abstracts;

import pure.bershka.core.utilities.result.DataResult;
import pure.bershka.core.utilities.result.Result;
import pure.bershka.entities.concretes.Size;

import java.util.List;

public interface SizeService {

    DataResult<List<Size>> getAllSizes();
    Result addSize(Size size);
    Result deleteSize(int sizeId);
}
