package pure.bershka.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pure.bershka.business.abstracts.DiscountCodeService;
import pure.bershka.core.utilities.result.Result;
import pure.bershka.core.utilities.result.SuccessResult;
import pure.bershka.dataAccess.abstracts.DiscountCodeDao;
import pure.bershka.entities.concretes.DiscountCode;

@Service
public class DiscountCodeManager implements DiscountCodeService {
    private DiscountCodeDao discountCodeDao;

    @Autowired
    public DiscountCodeManager(DiscountCodeDao discountCodeDao) {
        this.discountCodeDao = discountCodeDao;
    }

    @Override
    public Result add(DiscountCode discountCode) {
        this.discountCodeDao.save(discountCode);
        return new SuccessResult();
    }

    @Override
    public Result delete(int discountCodeId) {
        DiscountCode deletedDiscountCode = this.discountCodeDao.findById(discountCodeId).get();
        this.discountCodeDao.delete(deletedDiscountCode);
        return new SuccessResult();
    }

    @Override
    public Result update(int discountCodeId, DiscountCode discountCode) {
        DiscountCode updatedDiscount = this.discountCodeDao.findById(discountCodeId).get();
        updatedDiscount.setDiscountCode(discountCode);
        this.discountCodeDao.save(updatedDiscount);
        return new SuccessResult();
    }
}
