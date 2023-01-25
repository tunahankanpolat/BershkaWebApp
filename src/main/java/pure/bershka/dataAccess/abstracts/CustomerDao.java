package pure.bershka.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import pure.bershka.core.utilities.result.DataResult;
import pure.bershka.core.utilities.result.Result;
import pure.bershka.entities.concretes.Customer;

import java.util.List;

public interface CustomerDao extends JpaRepository<Customer, Integer> {

    @Query("SELECT c FROM Customer c WHERE c.email = :email and c.password = :password")
    Customer getCustomerInfo(String email, String password); // yoksa email ve password mu??


    @Query("select c from Customer c where c.email = :email")
    Customer findByEmail(String email);

    @Query("select c from Customer c where c.isActive=false ")
    List<Customer> getAllByActiveIsFalse();

    @Query("select c from Customer c where c.isActive=true ")
    List<Customer> getAllActiveIsTrue();
}
