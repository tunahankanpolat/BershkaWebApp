package pure.bershka.business.abstracts;

import pure.bershka.core.utilities.result.Result;
import pure.bershka.entities.concretes.Image;

import pure.bershka.core.utilities.result.DataResult;
import pure.bershka.entities.concretes.Image;

import java.util.List;

public interface ImageService {

    Result add(Image image);
    Result delete(int imageId);
    Result update(int imageId, Image image);
    DataResult<List<Image>> getProductImages(int productId);
}
