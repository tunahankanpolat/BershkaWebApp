
package pure.bershka.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pure.bershka.entities.concretes.Customer;
import pure.bershka.entities.concretes.User;

@Repository
public interface UserDao extends JpaRepository<Customer,Integer> {
	// @Query("SELECT u FROM Customer User u where u")
	//User getUserByEmail(String email);
}

