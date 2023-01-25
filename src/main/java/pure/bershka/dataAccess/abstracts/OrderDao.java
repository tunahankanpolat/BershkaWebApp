package pure.bershka.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pure.bershka.entities.concretes.Order;

import java.util.List;

public interface OrderDao extends JpaRepository<Order,Integer> {

}
