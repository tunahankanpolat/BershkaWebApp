package pure.bershka.business.concretes;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pure.bershka.business.abstracts.CustomerService;
import pure.bershka.core.utilities.result.*;
import pure.bershka.dataAccess.abstracts.*;
import pure.bershka.entities.concretes.*;
import pure.bershka.entities.dtos.CustomerDto;

@Service
public class CustomerManager implements CustomerService {
	private CustomerDao customerDao;
	private StockDao stockDao;
	private ProductDao productDao;
	private LocationDao locationDao;

	private SizeDao sizeDao;
	private ItemDao itemDao;

	@Autowired
	public CustomerManager(CustomerDao customerDao, StockDao stockDao,
						   ProductDao productDao,LocationDao locationDao,
						   SizeDao sizeDao, ItemDao itemDao) {
		this.customerDao=customerDao;
		this.stockDao = stockDao;
		this.productDao = productDao;
		this.locationDao = locationDao;
		this.sizeDao = sizeDao;
		this.itemDao = itemDao;
	}

	@Override
	public Result signUp(CustomerDto customerDto) {

		if (this.customerDao.findByEmail(customerDto.getEmail()) != null) {
			return new ErrorResult("Kullanıcı Mevcut");
		} else {

			Location location = new Location();
			location.setAddress(customerDto.getAddress());
			location.setCity(customerDto.getCity());
			location.setPostCode(customerDto.getPostalCode());
			location.setTitle(customerDto.getAddressTitle());
			location.setTown(customerDto.getTown());
			location.setId(0);
			this.locationDao.save(location);

			Customer customer = new Customer();
			customer.setActive(false);
			customer.setAddDate(LocalDateTime.now());
			customer.setEmail(customerDto.getEmail());
			customer.setFirstName(customerDto.getFirstName());
			customer.setLastName(customerDto.getLastName());
			customer.setId(0);
			customer.setIdentityNumber(customerDto.getIdentityNumber());
			customer.setPassword(customerDto.getPassword());
			customer.setPhoneNumber(customerDto.getPhoneNumber());
			this.customerDao.save(customer);
			return new SuccessResult("Başarılı");

		}
		
	}



	// FOR ADMIN
	@Override
	public Result updateCustomer(int customerId, Customer customer){
		Customer updatedCustomer = this.customerDao.findById(customerId).get();
		updatedCustomer.setCustomer(customer);
		this.customerDao.save(updatedCustomer);
		return new SuccessResult();
	}
	@Override
	public Result deleteCustomer(int customerId){
		Customer deletedCustomer = this.customerDao.findById(customerId).get();
		this.customerDao.delete(deletedCustomer);
		return new SuccessResult();

	}

	@Override
	public DataResult<Customer> getCustomerById(int id) {
		Customer customer = this.customerDao.findById(id).get();
		return new SuccessDataResult<Customer>(customer);
	}

	@Override
	public DataResult<List<Item>> getBasket(int customerId) {
		Customer customer = this.customerDao.findById(customerId).get();
		return new SuccessDataResult<>(customer.getBasket());
	}

	@Override
	public DataResult<List<Product>> getFavorite(int customerId) {
		Customer customer = this.customerDao.findById(customerId).get();
		return new SuccessDataResult<>(customer.getFavorites());
	}

	@Override
	public DataResult<List<Location>> getLocation(int customerId) {
		Customer customer = this.customerDao.findById(customerId).get();
		return new SuccessDataResult<>(customer.getLocations());
	}

	@Override
	public DataResult<List<Refund>> getRefund(int customerId) {
		Customer customer = this.customerDao.findById(customerId).get();
		return new SuccessDataResult<>(customer.getRefunds());
	}

	@Override
	public DataResult<List<Order>> getOrder(int customerId) {
		Customer customer = this.customerDao.findById(customerId).get();
		return new SuccessDataResult<>(customer.getOrders());
	}

	@Override
	public DataResult<List<Customer>> getAll() {
		return new SuccessDataResult<>(this.customerDao.findAll());
	}

	@Override
	public DataResult<List<Customer>> getAllInActive() {
		return new SuccessDataResult<>(this.customerDao.getAllByActiveIsFalse(),"Inactive müşteriler getirildi.");
	}

	@Override
	public DataResult<List<Customer>> getAllActive() {
		return new SuccessDataResult<>(this.customerDao.getAllActiveIsTrue());
	}

	@Override
	public Result increaseBudget(int customerId, int amount) {
		Customer customer = this.customerDao.findById(customerId).get();
		int previousBudget = customer.getBudget();
		customer.setBudget(previousBudget + amount);
		this.customerDao.save(customer);
		return new SuccessResult("Müşterinin bütçesi arttırıldı.");
	}

	@Override
	public Result changeEmailPassword(int customerId, String email, String password) {
		Customer customer = this.customerDao.findById(customerId).get();
		String previousEmail = customer.getEmail();
		String previousPassword = customer.getPassword();
		if (!previousEmail.equals(email) && previousPassword.equals(password)){
			customer.setEmail(email);
			this.customerDao.save(customer);
			return new SuccessResult("Email başarı ile güncellendi.");
		}
		else if (!previousPassword.equals(password) && previousEmail.equals(email)){
			customer.setPassword(password);
			this.customerDao.save(customer);
			return new SuccessResult("Password başarı ile güncellendi.");
		}
		else if (!previousPassword.equals(password) && !previousEmail.equals(email)){
			customer.setEmail(email);
			customer.setPassword(password);
			this.customerDao.save(customer);
			return new SuccessResult("Email ve password değişikliği yapıldı");
		}
		else if (previousPassword.equals(password) && previousEmail.equals(email))
			return new ErrorResult("Girilen bilgiler, önceki bilgiler ile aynı!");

		return new ErrorResult("Güncelleme işlemince bir hata oluştu");


	}

	@Override
	public Result removeFavorites(int customerId, int productId) {
		Customer removeFromFavorite = this.customerDao.findById(customerId).get();
		if (removeFromFavorite.getFavorites().size() != 0){
			for (int i = 0; i < removeFromFavorite.getFavorites().size(); i++) {
				if(removeFromFavorite.getFavorites().get(i).getId() == productId){
					removeFromFavorite.getFavorites().remove(i);
					this.customerDao.save(removeFromFavorite);
					return new SuccessResult("Ürün favorilerden çıkarıldı.");
				}
			}

		}
		return new ErrorResult("Ürün favorilerden çıkarılamadı!!");
	}

	@Override
	public Result addFavorite(int customerId, int productId) {
		Customer toAdd = this.customerDao.findById(customerId).get();
		Product product = this.productDao.findById(productId).get();
		if (toAdd.getFavorites().contains(product))
			return new ErrorResult("Ürün favorilerde bulunuyor.");
		else {
			toAdd.getFavorites().add(product);
			this.customerDao.save(toAdd);
			return new SuccessResult("Ürün favorilere eklendi.");
		}
	}

	@Override
	public Result addBasket(int customerId, int productId, int sizeId, int amount) {
		Customer toAddBasket = this.customerDao.findById(customerId).get();
		Product product = this.productDao.findById(productId).get();
		Size size = this.sizeDao.findById(sizeId).get();
		boolean flag =false;
		if (toAddBasket.getBasket().size() != 0){
			for (int i = 0; i < toAddBasket.getBasket().size(); i++) {
				if (toAddBasket.getBasket().get(i).getProduct().getId() == productId &&
						toAddBasket.getBasket().get(i).getSize().getId() == sizeId){
					int previousAmount = toAddBasket.getBasket().get(i).getAmount();
					toAddBasket.getBasket().get(i).setAmount(previousAmount + amount);
					this.customerDao.save(toAddBasket);
					return new SuccessResult("Ürün sepete eklendi.");
				}
			}
		}
		Item item = new Item(product,size, amount);
		toAddBasket.getBasket().add(item);
		this.itemDao.save(item);
		this.customerDao.save(toAddBasket);
		return new SuccessResult("Ürün sepete eklendi.");

	}

	@Override
	public Result removeBasket(int customerId, int productId,int sizeId) {
		Customer removeFromBasket = this.customerDao.findById(customerId).get();
		if (removeFromBasket.getBasket().size()!=0){
			for (int i = 0; i < removeFromBasket.getBasket().size(); i++) {
				if(removeFromBasket.getBasket().get(i).getProduct().getId() == productId &&
						removeFromBasket.getBasket().get(i).getSize().getId() == sizeId){
					if(removeFromBasket.getBasket().get(i).getAmount() == 1){
						removeFromBasket.getBasket().remove(i);
						Item itemToDelete = this.itemDao.getItemByProductIdAndSizeId(productId,sizeId);
						this.customerDao.save(removeFromBasket);
						this.itemDao.delete(itemToDelete);
						return new SuccessResult("Ürün sepetten çıkarıldı.");
					}
					else {
						int newAmount = removeFromBasket.getBasket().get(i).getAmount();
						removeFromBasket.getBasket().get(i).setAmount(newAmount - 1);
						this.customerDao.save(removeFromBasket);
						return new SuccessResult("Ürün sepetten çıkarıldı.");
					}

				}
			}
		}
		return new ErrorResult("Ürün sepetten çıkarılamadı!!!");
	}

	@Override
	public Result addLocation(int customerId,String title, String address, String city, String town, String postCode) {
		Location location = new Location(title,address,city,town,postCode);
		Customer customer = this.customerDao.findById(customerId).get();
		this.locationDao.save(location);
		customer.getLocations().add(location);
		this.customerDao.save(customer);
		return new SuccessResult("Address oluşturuldu.");
	}

}
