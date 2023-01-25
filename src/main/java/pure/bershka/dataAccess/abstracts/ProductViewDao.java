package pure.bershka.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;
import pure.bershka.entities.concretes.ProductView;

import java.util.List;

@Repository
public interface ProductViewDao extends JpaRepository<ProductView, Integer> {
    List<ProductView> findAll();
}
