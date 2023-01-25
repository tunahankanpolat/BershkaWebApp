package pure.bershka.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pure.bershka.entities.concretes.Refund;

import java.util.List;

public interface RefundDao extends JpaRepository<Refund,Integer> {

}
