package pure.bershka.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import pure.bershka.entities.concretes.Item;

public interface ItemDao extends JpaRepository<Item, Integer> {

    Item getItemByProductIdAndSizeId(int productId,int sizeId);
}
