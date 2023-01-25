package pure.bershka.business.abstracts;

import pure.bershka.core.utilities.result.Result;

public interface AdminService {

    Result setActive(int customerId);
    Result setInActive(int customerId);
}
