package pure.bershka.dataAccess.abstracts;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import pure.bershka.entities.concretes.Size;

public interface SizeDao extends JpaRepository<Size,Integer> {
	

}
