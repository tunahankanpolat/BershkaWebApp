package pure.bershka.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pure.bershka.business.abstracts.TypologyService;
import pure.bershka.core.utilities.result.DataResult;
import pure.bershka.core.utilities.result.Result;
import pure.bershka.core.utilities.result.SuccessDataResult;
import pure.bershka.core.utilities.result.SuccessResult;
import pure.bershka.dataAccess.abstracts.TypologyDao;
import pure.bershka.entities.concretes.Gender;
import pure.bershka.entities.concretes.Typology;

import java.util.ArrayList;
import java.util.List;

@Service
public class TypologyManager implements TypologyService {
    private TypologyDao TypologyDao;
    @Autowired
    public TypologyManager(pure.bershka.dataAccess.abstracts.TypologyDao typologyDao) {
        TypologyDao = typologyDao;
    }

    @Override
    public DataResult<List<Typology>> listCategories(int categoryId) {
        return new SuccessDataResult<List<Typology>>(this.TypologyDao.getByCategory_Id(categoryId));
    }



    // FOR ADMIN
    @Override
    public Result addTypology(Typology typology) {
        this.TypologyDao.save(typology);
        return new SuccessResult("true");
    }

    @Override
    public Result updateTypology(int typologyId, Typology typology) {
        Typology updatedTypology = this.TypologyDao.findById(typologyId).get();
        updatedTypology.setTypology(typology);
        this.TypologyDao.save(updatedTypology);
        return new SuccessResult("true");
    }

    @Override
    public Result deleteTypology(int typologyId) {
        Typology deletedTypology = this.TypologyDao.findById(typologyId).get();
        this.TypologyDao.delete(deletedTypology);
        return new SuccessResult("true");
    }

	@Override
	public DataResult<List<Typology>> listCategoriesWithGender(int categoryId, String gender) {
		if(gender.equalsIgnoreCase("ERKEK")) {
			gender="MALE";
		}else {
			gender="FEMALE";

		}
		List<Typology> listUnisex =this.TypologyDao.getByCategory_IdAndGender(categoryId, "UNISEX");
		List<Typology> listGender =this.TypologyDao.getByCategory_IdAndGender(categoryId, gender.toUpperCase());
		List<Typology> newList = new ArrayList<Typology>(listUnisex);
		newList.addAll(listGender);
		return new SuccessDataResult<List<Typology>>(newList);
	}


}
