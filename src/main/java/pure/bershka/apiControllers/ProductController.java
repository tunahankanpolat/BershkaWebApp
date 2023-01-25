package pure.bershka.apiControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pure.bershka.business.abstracts.ProductService;
import pure.bershka.core.utilities.result.DataResult;
import pure.bershka.core.utilities.result.Result;
import pure.bershka.entities.concretes.*;
import pure.bershka.entities.dtos.ProductDto;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/product")
public class ProductController {
	private ProductService productService;

	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping("/get-all")
	public DataResult<List<Product>> getAll() {
		return this.productService.getAll();
	}

	@PostMapping("/add")
	public Result add(@RequestBody ProductDto productDto) {
		return this.productService.add(productDto);
	}

	@PutMapping("/update")
	public Result update(int id, @RequestBody Product product) {
		return this.productService.update(id, product);
	}

	@DeleteMapping("/delete")
	public Result delete(int id) {
		return this.productService.delete(id);
	}

	@GetMapping("/get-product")
	public DataResult<Product> getProduct(int id) {
		return this.productService.getProduct(id);
	}

	@GetMapping("/get-by-name-like")
	public DataResult<List<Product>> getByNameLike(String name) {
		return this.productService.getByNameLike(name);
	}

	@GetMapping("/get-by-gender")
	public DataResult<List<Product>> getByGender(@RequestParam Gender gender) {
		return this.productService.getByGender(gender);
	}

	@GetMapping("/get-discounted-products")
	public DataResult<List<Product>> getDiscountedProducts() {
		return this.productService.getDiscountedProducts();
	}

	@GetMapping("/get-by-filter-typology-size-color-price")
	public DataResult<List<Product>> getByFilterTypologyAndSizeAndColorAndPrice(String typology, String size,
			String color, String min, String max) {
		return this.productService.getByFilterTypologyAndSizeAndColorAndPrice(typology, size, color, BigDecimal.valueOf(Double.parseDouble(min)), BigDecimal.valueOf(Double.parseDouble(max)));
	}

	@GetMapping("/get-by-filter-typology-gender")
	public DataResult<List<Product>> getByFilterTypologyAndGender(String typology, String gender) {
		return this.productService.getByFilterTypologyAndGender(typology, gender);
	}

	@GetMapping("/get-by-filter-typology-size-color")
	public DataResult<List<Product>> getByFilterTypologyAndSizeAndColor(String typology, String size, String color) {
		return this.productService.getByFilterTypologyAndSizeAndColor(typology, size, color);
	}

	@GetMapping("/get-by-filter-typology-size-price")
	public DataResult<List<Product>> getByFilterTypologyAndSizeAndPrice(String typology, String size, String min,
			String max) {
		return this.productService.getByFilterTypologyAndSizeAndPrice(typology, size, BigDecimal.valueOf(Double.parseDouble(min)), BigDecimal.valueOf(Double.parseDouble(max)));
	}

	@GetMapping("/get-by-filter-typology-color-price")
	public DataResult<List<Product>> getByFilterTypologyAndColorAndPrice(String typology, String color, String min,
			String max) {
		return this.productService.getByFilterTypologyAndColorAndPrice(typology, color, BigDecimal.valueOf(Double.parseDouble(min)), BigDecimal.valueOf(Double.parseDouble(max)));
	}

	@GetMapping("/get-by-filter-typology-size")
	public DataResult<List<Product>> getByFilterTypologyAndSize(String typology, String size) {
		return this.productService.getByFilterTypologyAndSize(typology, size);
	}

	@GetMapping("/get-by-filter-typology-color")
	public DataResult<List<Product>> getByFilterTypologyAndColor(String typology, String color) {
		return this.productService.getByFilterTypologyAndColor(typology, color);
	}

	@GetMapping("/get-by-filter-typology-price")
	public DataResult<List<Product>> getByFilterTypologyAndPrice(String typology, String min, String max) {
		return this.productService.getByFilterTypologyAndPrice(typology, BigDecimal.valueOf(Double.parseDouble(min)), BigDecimal.valueOf(Double.parseDouble(max)));
	}

	@GetMapping("/get-by-filter-typology")
	public DataResult<List<Product>> getByFilterTypology(String typology) {
		return this.productService.getByFilterTypology(typology);
	}

	@GetMapping("/get-by-filter-category-size-color-price")
	public DataResult<List<Product>> getByFilterCategoryAndSizeAndColorAndPrice(String category, String size,
			String color, String min, String max) {
		return this.productService.getByFilterCategoryAndSizeAndColorAndPrice(category, size, color, BigDecimal.valueOf(Double.parseDouble(min)), BigDecimal.valueOf(Double.parseDouble(max)));
	}

	@GetMapping("/get-by-filter-category-size-color")
	public DataResult<List<Product>> getByFilterCategoryAndSizeAndColor(String category, String size, String color) {
		return this.productService.getByFilterCategoryAndSizeAndColor(category, size, color);
	}

	@GetMapping("/get-by-filter-category-size-price")
	public DataResult<List<Product>> getByFilterCategoryAndSizeAndPrice(String category, String size, String min,
			String max) {
		return this.productService.getByFilterCategoryAndSizeAndPrice(category, size, BigDecimal.valueOf(Double.parseDouble(min)), BigDecimal.valueOf(Double.parseDouble(max)));
	}

	@GetMapping("/get-by-filter-category-color-price")
	public DataResult<List<Product>> getByFilterCategoryAndColorAndPrice(String category, String color, String min,
			String max) {
		return this.productService.getByFilterCategoryAndColorAndPrice(category, color, BigDecimal.valueOf(Double.parseDouble(min)), BigDecimal.valueOf(Double.parseDouble(max)));
	}

	@GetMapping("/get-by-filter-category-size")
	public DataResult<List<Product>> getByFilterCategoryAndSize(String category, String size) {
		return this.productService.getByFilterCategoryAndSize(category, size);
	}

	@GetMapping("/get-by-filter-category-color")
	public DataResult<List<Product>> getByFilterCategoryAndColor(String category, String color) {
		return this.productService.getByFilterCategoryAndColor(category, color);
	}

	@GetMapping("/get-by-filter-category-price")
	public DataResult<List<Product>> getByFilterCategoryAndPrice(String category, String min, String max) {
		return this.productService.getByFilterCategoryAndPrice(category, BigDecimal.valueOf(Double.parseDouble(min)), BigDecimal.valueOf(Double.parseDouble(max)));
	}

	@GetMapping("/get-by-filter-category")
	public DataResult<List<Product>> getByFilterCategory(String category) {
		return this.productService.getByFilterCategory(category);
	}
}
