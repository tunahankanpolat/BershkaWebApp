package pure.bershka.apiControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pure.bershka.business.abstracts.CategoryService;
import pure.bershka.core.utilities.result.DataResult;
import pure.bershka.core.utilities.result.Result;
import pure.bershka.entities.concretes.Category;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/category")
public class CategoryController {
	private CategoryService categoryService;

	@Autowired
	public CategoryController(CategoryService categoryService) {
		super();
		this.categoryService = categoryService;
	}

	@PostMapping(value = "/add")
	public Result add( String category) {
		return this.categoryService.add(category);
	}

	@GetMapping("/get-all")
	public DataResult<List<Category>> getAll() {
		System.out.println();
		return this.categoryService.getAll();
	};

	@PutMapping("/update")
	public Result update(int categoryId, @RequestBody Category category){
		return this.categoryService.updateCategory(categoryId,category);
	}
	@DeleteMapping("/delete")
	public Result delete(int categoryId){
		return this.categoryService.deleteCategory(categoryId);
	}
}
