
package pure.bershka.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pure.bershka.business.abstracts.UserService;
import pure.bershka.core.utilities.result.*;
import pure.bershka.dataAccess.abstracts.AdminDao;
import pure.bershka.dataAccess.abstracts.CustomerDao;
import pure.bershka.dataAccess.abstracts.UserDao;
import pure.bershka.entities.concretes.Admin;
import pure.bershka.entities.concretes.Customer;
import pure.bershka.entities.concretes.User;

import java.util.List;

@Service
public class UserManager implements UserService {
    private UserDao userDao;
	private CustomerDao customerDao;
	private AdminDao adminDao;
    @Autowired
    public UserManager(UserDao userDao,CustomerDao customerDao,AdminDao adminDao) {
        this.userDao = userDao;
		this.customerDao = customerDao;
		this.adminDao = adminDao;
    }



	@Override
	public DataResult<User> signIn(String email, String password) {
		if (this.adminDao.getByEmail(email) != null){
			List<Admin> toCheck = this.adminDao.findAll();
			Admin admin = null;
			boolean flag = false;
			for (int i = 0; i < toCheck.size(); i++) {
				if (toCheck.get(i).getEmail().equals(email)){
					flag = true;
					admin = toCheck.get(i);
					break;		// Admin found
					// return new SuccessDataResult<>(this.customerDao.getCustomerInfo(email,password),"Başarılı");
				}
			}
			if (flag && !admin.getPassword().equals(password))
				return new ErrorDataResult<>("Invalid password!");
			else if (flag && admin.getPassword().equals(password)) {
				return new SuccessDataResult<>(this.adminDao.getByEmail(email),"Admin");
			}
		}
		else {
			List<Customer> toCheck = this.customerDao.findAll();
			Customer customer = null;
			boolean flag = false;
			for (int i = 0; i < toCheck.size(); i++) {
				if (toCheck.get(i).getEmail().equals(email)){
					flag = true;
					customer = toCheck.get(i);
					break;		// Customer found
					// return new SuccessDataResult<>(this.customerDao.getCustomerInfo(email,password),"Başarılı");
				}
			}

			if (flag && !customer.getPassword().equals(password))
				return new ErrorDataResult<>("Invalid password!");

			if (flag && customer.getPassword().equals(password) && !customer.isActive())
				return new ErrorDataResult<>("Customer found but is not active.");

			if (!flag)
				return new ErrorDataResult<>("Customer not found!");
			return new SuccessDataResult<>(this.customerDao.getCustomerInfo(email,password),"Başarılı");
		}

		return new ErrorDataResult<>("No user found.");
	}
}

