package pure.bershka.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pure.bershka.business.abstracts.ColorService;
import pure.bershka.core.utilities.result.DataResult;
import pure.bershka.core.utilities.result.Result;
import pure.bershka.core.utilities.result.SuccessDataResult;
import pure.bershka.core.utilities.result.SuccessResult;
import pure.bershka.dataAccess.abstracts.ColorDao;
import pure.bershka.entities.concretes.Color;

@Service
public class ColorManager implements ColorService {
    private ColorDao colorDao;

    @Autowired
    public ColorManager(ColorDao colorDao) {
        this.colorDao = colorDao;
    }

	@Override
	public Result add(Color color) {
		this.colorDao.save(color);
		return new SuccessResult("Renk Eklendi");
	}

	@Override
	public DataResult<List<Color>> getAll() {
		return new SuccessDataResult<List<Color>>(this.colorDao.findAll(), "succes");
	}

	@Override
	public Result deleteColor(int colorId) {
		Color deletedColor = this.colorDao.findById(colorId).get();
		this.colorDao.delete(deletedColor);
		return new SuccessResult();
	}
}
