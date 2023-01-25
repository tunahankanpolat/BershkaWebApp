package pure.bershka.business.abstracts;

import org.springframework.data.jpa.repository.Query;
import pure.bershka.core.utilities.result.DataResult;
import pure.bershka.core.utilities.result.Result;
import pure.bershka.entities.concretes.*;
import pure.bershka.entities.dtos.ProductDto;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

public interface ProductService {
    DataResult<List<Product>> getAll();
    Result add(ProductDto productDto);

    Result update(int productId, Product product);

    Result delete(int productId);

    DataResult<Product> getProduct(int id);

    DataResult<List<Product>> getByNameLike(String name);

    DataResult<List<Product>> getByGender(Gender gender);

    DataResult<List<Product>> getDiscountedProducts();

    DataResult<List<Product>> getByFilterTypologyAndSizeAndColorAndPrice(String typology, String size, String color, BigDecimal min, BigDecimal max);

    DataResult<List<Product>> getByFilterTypology(String typology);
    
    DataResult<List<Product>> getByFilterTypologyAndGender(String typology,String gender);

    // ------------------------------------- For page in filter
    DataResult<List<Product>> getByFilterTypologyAndSize(String typology, String size);

    DataResult<List<Product>> getByFilterTypologyAndColor(String typology, String color);

    DataResult<List<Product>> getByFilterTypologyAndPrice(String typology, BigDecimal min, BigDecimal max);

    DataResult<List<Product>> getByFilterTypologyAndSizeAndColor(String typology, String size, String color);

    DataResult<List<Product>> getByFilterTypologyAndSizeAndPrice(String typology, String size,BigDecimal min, BigDecimal max);

    DataResult<List<Product>> getByFilterTypologyAndColorAndPrice(String typology, String color,BigDecimal min, BigDecimal max);

    ///**********///

    DataResult<List<Product>> getByFilterCategoryAndSizeAndColorAndPrice(String category, String size, String color, BigDecimal min, BigDecimal max);

    DataResult<List<Product>> getByFilterCategory(String category);

    DataResult<List<Product>> getByFilterCategoryAndSize(String category, String size);

    DataResult<List<Product>> getByFilterCategoryAndColor(String category, String color);

    DataResult<List<Product>> getByFilterCategoryAndPrice(String category, BigDecimal min, BigDecimal max);

    DataResult<List<Product>> getByFilterCategoryAndSizeAndColor(String category, String size, String color);

    DataResult<List<Product>> getByFilterCategoryAndSizeAndPrice(String category, String size,BigDecimal min, BigDecimal max);

    DataResult<List<Product>> getByFilterCategoryAndColorAndPrice(String category, String color,BigDecimal min, BigDecimal max);

}
