package pure.bershka.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pure.bershka.business.abstracts.ImageService;
import pure.bershka.core.utilities.result.Result;
import pure.bershka.core.utilities.result.SuccessResult;
import pure.bershka.core.utilities.result.DataResult;
import pure.bershka.core.utilities.result.SuccessDataResult;
import pure.bershka.dataAccess.abstracts.ImageDao;
import pure.bershka.entities.concretes.Image;
import pure.bershka.entities.concretes.Image;

import java.util.List;

@Service
public class ImageManager implements ImageService {
    private ImageDao imageDao;

    @Autowired
    public ImageManager(ImageDao imageDao) {
        this.imageDao = imageDao;
    }

    // FOR ADMIN
    @Override
    public Result add(Image image) {
        this.imageDao.save(image);
        return new SuccessResult();
    }

    @Override
    public Result delete(int imageId) {
        Image deletedImage = this.imageDao.findById(imageId).get();
        this.imageDao.delete(deletedImage);
        return new SuccessResult();
    }

    @Override
    public Result update(int imageId, Image image) {
        Image updatedImage = this.imageDao.findById(imageId).get();
        updatedImage.setImage(image);
        this.imageDao.save(updatedImage);
        return new SuccessResult();
    }


    @Override
    public DataResult<List<Image>> getProductImages(int productId) {
        return new SuccessDataResult<>(this.imageDao.getByProduct_Id(productId));
    }
}
