package pure.bershka.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pure.bershka.entities.concretes.Color;

@Repository
public interface ColorDao extends JpaRepository<Color,Integer> {
	Color getById(int id);

}
