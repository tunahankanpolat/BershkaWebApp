package pure.bershka.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pure.bershka.entities.concretes.Product;
import pure.bershka.entities.concretes.Stock;

@Repository
public interface StockDao extends JpaRepository<Stock,Integer> {

    @Query("SELECT s FROM Stock s WHERE s.product.id = :productId AND s.size.id = :sizeId")
    Stock getProduct(int productId, int sizeId);
    
    @Query("SELECT s FROM Stock s WHERE s.product.id = :productId")
    List<Stock> getProductSizes(int productId);
    
}
