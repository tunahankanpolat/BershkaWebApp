package pure.bershka.business.abstracts;

import pure.bershka.core.utilities.result.Result;
import pure.bershka.entities.concretes.DiscountCode;

public interface DiscountCodeService {
    Result add(DiscountCode discountCode);
    Result delete(int discountCodeId);
    Result update(int discountCodeId, DiscountCode discountCode);
}
