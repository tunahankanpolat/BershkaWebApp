package pure.bershka.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pure.bershka.business.abstracts.CategoryService;
import pure.bershka.core.utilities.result.DataResult;
import pure.bershka.core.utilities.result.Result;
import pure.bershka.core.utilities.result.SuccessDataResult;
import pure.bershka.core.utilities.result.SuccessResult;
import pure.bershka.dataAccess.abstracts.CategoryDao;
import pure.bershka.entities.concretes.Category;

import java.util.List;

@Service
public class CategoryManager implements CategoryService {
    private CategoryDao categoryDao;

    @Autowired
    public CategoryManager(CategoryDao categoryDao) {
    	super();
        this.categoryDao = categoryDao;
    }



    @Override
    public DataResult<List<Category>> getAll() {
        return new SuccessDataResult<>(this.categoryDao.findAll(), "succes");
    }

    // FOR ADMIN
    @Override
    public Result add(String categoryName) {
        this.categoryDao.storedProcedure(categoryName);
        return new SuccessResult("Kategori eklendi");
    }
    @Override
    public Result updateCategory(int categoryId, Category category) {
        Category updatedCategory = this.categoryDao.findById(categoryId).get();
        updatedCategory.setCategory(category);
        this.categoryDao.save(updatedCategory);
        return new SuccessResult();
    }

    @Override
    public Result deleteCategory(int categoryId) {
        Category deletedCategory = this.categoryDao.findById(categoryId).get();
        this.categoryDao.delete(deletedCategory);
        return new SuccessResult();
    }


}
