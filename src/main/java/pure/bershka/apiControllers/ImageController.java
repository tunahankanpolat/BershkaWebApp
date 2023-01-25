package pure.bershka.apiControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pure.bershka.business.abstracts.ImageService;
import pure.bershka.core.utilities.result.Result;
import pure.bershka.entities.concretes.Image;
import pure.bershka.entities.concretes.Stock;
import pure.bershka.core.utilities.result.DataResult;
import pure.bershka.entities.concretes.Image;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/image")
public class ImageController {
    private ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("/add")
    public Result add(@RequestBody Image image){
        return this.imageService.add(image);
    }
    @PutMapping("/update")
    public Result update(int imageId, @RequestBody Image image){
        return this.imageService.update(imageId, image);
    }
    @DeleteMapping("/delete")
    public Result delete(int imageId){
        return this.imageService.delete(imageId);
    }

    @GetMapping("/get-product-images")
    public DataResult<List<Image>> getProductImages(int productId){
        return this.imageService.getProductImages(productId);
    }
}
