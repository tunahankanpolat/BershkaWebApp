package pure.bershka.business.abstracts;

import pure.bershka.apiControllers.CustomerController;
import pure.bershka.core.utilities.result.DataResult;
import pure.bershka.core.utilities.result.Result;
import pure.bershka.entities.concretes.*;
import pure.bershka.entities.dtos.CustomerDto;

import java.util.List;

public interface CustomerService {
	
	Result signUp(CustomerDto customerDto);
	Result updateCustomer(int customerId, Customer customer);
	Result deleteCustomer(int customerId);
	Result removeFavorites(int customerId, int productId);
	Result addFavorite(int customerId, int productId);
	Result addBasket(int customerId, int productId, int sizeId, int amount);
	Result removeBasket(int customerId, int productId,int sizeId);
	Result addLocation(int customerId,String title, String address, String city, String town, String postCode);
	DataResult<Customer> getCustomerById(int id);

	DataResult<List<Item>> getBasket(int customerId);
	DataResult<List<Product>> getFavorite(int customerId);
	DataResult<List<Location>> getLocation(int customerId);
	DataResult<List<Refund>> getRefund(int customerId);
	DataResult<List<Order>> getOrder(int customerId);
	DataResult<List<Customer>> getAll();
	DataResult<List<Customer>> getAllInActive();
	DataResult<List<Customer>> getAllActive();
	Result increaseBudget(int customerId, int amount);
	Result changeEmailPassword(int customerId, String email, String password);


}
