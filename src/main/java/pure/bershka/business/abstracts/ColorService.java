package pure.bershka.business.abstracts;

import java.util.List;

import pure.bershka.core.utilities.result.DataResult;
import pure.bershka.core.utilities.result.Result;
import pure.bershka.entities.concretes.Color;

public interface ColorService {
    Result add(Color color );
    DataResult<List<Color>> getAll();
    Result deleteColor(int colorId);
}
