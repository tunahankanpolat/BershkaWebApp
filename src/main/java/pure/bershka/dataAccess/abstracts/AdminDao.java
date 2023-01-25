package pure.bershka.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pure.bershka.core.utilities.result.DataResult;
import pure.bershka.entities.concretes.Admin;
import pure.bershka.entities.concretes.Customer;

import java.util.List;
@Repository
public interface AdminDao extends JpaRepository<Admin, Integer> {

	@Query("SELECT a from Admin a where a.email = :email")
	Admin getByEmail(String email);
	
}
