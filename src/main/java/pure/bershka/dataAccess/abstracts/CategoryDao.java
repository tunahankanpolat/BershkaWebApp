package pure.bershka.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pure.bershka.entities.concretes.Category;

import javax.transaction.Transactional;

@Repository
public interface CategoryDao extends JpaRepository<Category, Integer> {
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "CALL ADD_CATEGORY(:categoryName);", nativeQuery = true)
    void storedProcedure(@Param("categoryName") String categoryName);
}
