package pure.bershka.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pure.bershka.entities.concretes.DiscountCode;

@Repository
public interface DiscountCodeDao extends JpaRepository<DiscountCode,Integer> {

    @Query("select d FROM DiscountCode d where d.code = :code")
    DiscountCode getByCode(String code);
}
