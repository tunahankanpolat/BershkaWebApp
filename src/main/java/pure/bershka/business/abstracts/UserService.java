
package pure.bershka.business.abstracts;

import pure.bershka.core.utilities.result.DataResult;
import pure.bershka.core.utilities.result.Result;
import pure.bershka.entities.concretes.User;

public interface UserService {
    DataResult<User> signIn(String email , String password);

}
