package pure.bershka.business.abstracts;

import pure.bershka.core.utilities.result.DataResult;
import pure.bershka.core.utilities.result.Result;
import pure.bershka.entities.concretes.Refund;

import java.util.List;

public interface RefundService {

    DataResult<List<Refund>> findAllRefunds();

    Result add(Refund refund);
    Result delete(int refundId);
    Result startRefund(String cause, int orderDetailId);

    
}
