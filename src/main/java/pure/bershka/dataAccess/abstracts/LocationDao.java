package pure.bershka.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import pure.bershka.entities.concretes.Location;

public interface LocationDao extends JpaRepository<Location,Integer> {
}
