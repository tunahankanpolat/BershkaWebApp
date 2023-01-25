package pure.bershka.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pure.bershka.business.abstracts.AdminService;
import pure.bershka.core.utilities.result.ErrorResult;
import pure.bershka.core.utilities.result.Result;
import pure.bershka.core.utilities.result.SuccessResult;
import pure.bershka.dataAccess.abstracts.AdminDao;
import pure.bershka.dataAccess.abstracts.CustomerDao;
import pure.bershka.entities.concretes.Customer;

@Service
public class AdminManager implements AdminService {
	
	private AdminDao adminDao;
	private CustomerDao customerDao;
	
	@Autowired
	public AdminManager(AdminDao adminDao, CustomerDao customerDao) {
		this.adminDao=adminDao;
		this.customerDao = customerDao;
	}


	@Override
	public Result setActive(int customerId) {
		Customer customer = this.customerDao.findById(customerId).get();
		if (customer.isActive()){
			return new ErrorResult("Customer is already set true.");
		}
		else {
			customer.setActive(true);
			this.customerDao.save(customer);
			return new SuccessResult("Customer has been set true.");
		}
	}

	@Override
	public Result setInActive(int customerId) {
		Customer customer = this.customerDao.findById(customerId).get();
		if (!customer.isActive()){
			return new ErrorResult("Customer is already set false.");
		}
		else {
			customer.setActive(false);
			this.customerDao.save(customer);
			return new SuccessResult("Customer has been set false.");
		}
	}
}
