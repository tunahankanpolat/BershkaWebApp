package pure.bershka.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pure.bershka.business.abstracts.SizeService;
import pure.bershka.core.utilities.result.DataResult;
import pure.bershka.core.utilities.result.Result;
import pure.bershka.core.utilities.result.SuccessDataResult;
import pure.bershka.core.utilities.result.SuccessResult;
import pure.bershka.dataAccess.abstracts.SizeDao;
import pure.bershka.entities.concretes.Size;

import java.util.List;

@Service
public class SizeManager implements SizeService {
	private SizeDao sizeDao;

	@Autowired
	public SizeManager(SizeDao sizeDao) {
		this.sizeDao = sizeDao;
	}

	// FOR ADMIN
	@Override
	public DataResult<List<Size>> getAllSizes() {
		return new SuccessDataResult<List<Size>>(this.sizeDao.findAll());
	}

	@Override
	public Result addSize(Size size) {
		this.sizeDao.save(size);
		return new SuccessResult();

	}

	@Override
	public Result deleteSize(int sizeId) {
		Size deletedSize = this.sizeDao.findById(sizeId).get();
		this.sizeDao.delete(deletedSize);
		return new SuccessResult();
	}



}
