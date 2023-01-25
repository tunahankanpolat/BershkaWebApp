package pure.bershka.apiControllers;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import pure.bershka.business.abstracts.CustomerService;
import pure.bershka.core.utilities.result.DataResult;
import pure.bershka.core.utilities.result.Result;
import pure.bershka.entities.concretes.*;
import pure.bershka.entities.dtos.CustomerDto;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
@CrossOrigin
@NoArgsConstructor
public class CustomerController {
	private CustomerService customerService;
	
	@Autowired
	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	@PostMapping("/sign-up")
	public Result signUp(@RequestBody CustomerDto customerDto) {
		return this.customerService.signUp(customerDto);
	}
	

	@GetMapping("/get-customer-id")
	public DataResult<Customer> getCustomerById(int id){
		return this.customerService.getCustomerById(id);
	}
	@PutMapping("/update")
	public Result update(int customerId, @RequestBody Customer customer){
		return this.customerService.updateCustomer(customerId,customer);
	}
	@DeleteMapping("/delete")
	public Result delete(int customerId){
		return this.customerService.deleteCustomer(customerId);
	}

	@DeleteMapping("/remove-favorite")
	public Result removeFromFavorite(int customerId, int productId){
		return this.customerService.removeFavorites(customerId,productId);
	}

	@PostMapping("/add-favorite")
	public Result addToFavorite(int customerId, int productId){
		return this.customerService.addFavorite(customerId,productId);
	}

	@DeleteMapping("/delete-basket")
	public Result deleteFromBasket(int customerId, int productId,int sizeId){
		return this.customerService.removeBasket(customerId,productId,sizeId);
	}
	@PostMapping("/add-basket")
	public Result addToBasket(int customerId, int productId, int sizeId, int amount){
		return this.customerService.addBasket(customerId,productId,sizeId,amount);
	}
	@PutMapping("/add-location")
	public Result addLocation(int customerId,String title, String address, String city, String town, String postCode){
		return this.customerService.addLocation(customerId,title,address,city,town,postCode);
	}
	@GetMapping("/get-baskets")
	public DataResult<List<Item>> getBasket(int customerId){
		return this.customerService.getBasket(customerId);
	}@GetMapping("/get-favorites")
	public DataResult<List<Product>> getFavorite(int customerId){
		return this.customerService.getFavorite(customerId);
	}@GetMapping("/get-orders")
	public DataResult<List<Order>> getOrder(int customerId){
		return this.customerService.getOrder(customerId);
	}@GetMapping("/get-locations")
	public DataResult<List<Location>> getLocation(int customerId){
		return this.customerService.getLocation(customerId);
	}@GetMapping("/get-refunds")
	public DataResult<List<Refund>> getRefund(int customerId){
		return this.customerService.getRefund(customerId);
	}

	@GetMapping("/get-all")
	public DataResult<List<Customer>> getAll(){
		return this.customerService.getAll();
	}
	@GetMapping("/get-all-InActive")
	public DataResult<List<Customer>> getAllInActive(){
		return this.customerService.getAllInActive();
	}

	@GetMapping("/get-all-Active")
	public DataResult<List<Customer>> getAllActive(){
		return this.customerService.getAllActive();
	}

	@PostMapping("/increase-budget")
	public Result increaseBudget(int customerId, int amount){
		return this.customerService.increaseBudget(customerId,amount);
	}
	@PostMapping("/change-email-password")
	public Result changeEmailPassword(int customerId, String email, String password){
		return this.customerService.changeEmailPassword(customerId,email,password);
	}
}
