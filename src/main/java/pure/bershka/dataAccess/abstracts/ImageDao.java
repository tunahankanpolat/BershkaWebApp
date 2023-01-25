package pure.bershka.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import pure.bershka.entities.concretes.Image;

import java.util.List;

public interface ImageDao extends JpaRepository<Image,Integer> {
    List<Image> getByProduct_Id(int productId);
}
