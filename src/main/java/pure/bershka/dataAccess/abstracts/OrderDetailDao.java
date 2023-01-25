package pure.bershka.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pure.bershka.entities.concretes.OrderDetail;

import java.util.List;

public interface OrderDetailDao extends JpaRepository<OrderDetail,Integer> {



}
