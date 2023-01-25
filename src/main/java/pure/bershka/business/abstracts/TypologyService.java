package pure.bershka.business.abstracts;

import pure.bershka.core.utilities.result.DataResult;
import pure.bershka.core.utilities.result.Result;
import pure.bershka.entities.concretes.Gender;
import pure.bershka.entities.concretes.Typology;

import java.util.List;

public interface TypologyService {

    DataResult<List<Typology>> listCategories(int categoryId);
    DataResult<List<Typology>> listCategoriesWithGender(int categoryId,String gender);

    Result addTypology(Typology typology);
    Result updateTypology(int typologyId, Typology typology);
    Result deleteTypology(int typologyId);

}
