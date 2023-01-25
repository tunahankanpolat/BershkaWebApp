
package pure.bershka.apiControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pure.bershka.business.abstracts.UserService;
import pure.bershka.core.utilities.result.DataResult;
import pure.bershka.core.utilities.result.ErrorResult;
import pure.bershka.core.utilities.result.Result;
import pure.bershka.core.utilities.result.SuccessResult;
import pure.bershka.entities.concretes.Admin;
import pure.bershka.entities.concretes.Customer;
import pure.bershka.entities.concretes.User;

@CrossOrigin
@RestController
@RequestMapping("/api/user")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

	@GetMapping ("/sign-in")
    public DataResult<User> signIn(String email, String password){
        return this.userService.signIn(email,password);
    }

}

