package pure.bershka.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pure.bershka.entities.concretes.*;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

@Repository
public interface ProductDao extends JpaRepository<Product, Integer> {
    List<Product> getByNameContainsIgnoreCase(String name);

    List<Product> getByGender(Gender gender);

    List<Product> getByDiscountPercentageGreaterThan(BigDecimal min);

    @Query("SELECT p FROM Product p JOIN p.sizes s JOIN p.typology t JOIN p.color c WHERE t.name = :typology AND s.name = :size AND c.name = :color AND p.price >= :min AND p.price <= :max")
    List<Product> getByFilterTypologyAndSizeAndColorAndPrice(String typology, String size, String color, BigDecimal min, BigDecimal max);

    @Query("SELECT p FROM Product p JOIN p.typology t WHERE t.name = :typology")
    List<Product> getByFilterTypology(String typology);

    //@Query(value = "CALL get_by_typology_and_gender(:typologyInput, :genderInput);", nativeQuery = true)
    @Query("SELECT p FROM Product p JOIN p.typology t WHERE t.name = :typology AND t.gender = :gender ")
    List<Product> getByFilterTypologyAndGender( String typology,  String gender);
    
    @Query("SELECT p FROM Product p JOIN p.sizes s JOIN p.typology t WHERE t.name = :typology AND s.name = :size")
    List<Product> getByFilterTypologyAndSize(String typology, String size);

    @Query("SELECT p FROM Product p JOIN p.color c JOIN p.typology t WHERE t.name = :typology AND c.name = :color")
    List<Product> getByFilterTypologyAndColor(String typology, String color);

    @Query("SELECT p FROM Product p JOIN p.typology t WHERE t.name = :typology AND p.price >= :min AND p.price <= :max")
    List<Product> getByFilterTypologyAndPrice(String typology, BigDecimal min, BigDecimal max);

    @Query("SELECT p FROM Product p JOIN p.sizes s JOIN p.typology t JOIN p.color c WHERE t.name = :typology AND s.name = :size AND c.name = :color")
    List<Product> getByFilterTypologyAndSizeAndColor(String typology, String size, String color);

    @Query("SELECT p FROM Product p JOIN p.typology t JOIN p.sizes s WHERE s.name = :size AND t.name = :typology AND p.price >= :min AND p.price <= :max")
    List<Product> getByFilterTypologyAndSizeAndPrice(String typology, String size,BigDecimal min, BigDecimal max);

    @Query("SELECT p FROM Product p JOIN p.typology t JOIN p.color c WHERE c.name = :color AND t.name = :typology AND p.price >= :min AND p.price <= :max")
    List<Product> getByFilterTypologyAndColorAndPrice(String typology, String color,BigDecimal min, BigDecimal max);

    //----------------------

    @Query("SELECT p FROM Product p JOIN p.sizes s JOIN p.category t JOIN p.color c WHERE t.name = :category AND s.name = :size AND c.name = :color AND p.price >= :min AND p.price <= :max")
    List<Product> getByFilterCategoryAndSizeAndColorAndPrice(String category, String size, String color, BigDecimal min, BigDecimal max);

    @Query("SELECT p FROM Product p JOIN p.category t WHERE t.name = :category")
    List<Product> getByFilterCategory(String category);

    @Query("SELECT p FROM Product p JOIN p.sizes s JOIN p.category t WHERE t.name = :category AND s.name = :size")
    List<Product> getByFilterCategoryAndSize(String category, String size);

    @Query("SELECT p FROM Product p JOIN p.color c JOIN p.category t WHERE t.name = :category AND c.name = :color")
    List<Product> getByFilterCategoryAndColor(String category, String color);

    @Query("SELECT p FROM Product p JOIN p.category t WHERE t.name = :category AND p.price >= :min AND p.price <= :max")
    List<Product> getByFilterCategoryAndPrice(String category, BigDecimal min, BigDecimal max);

    @Query("SELECT p FROM Product p JOIN p.sizes s JOIN p.category t JOIN p.color c WHERE t.name = :category AND s.name = :size AND c.name = :color")
    List<Product> getByFilterCategoryAndSizeAndColor(String category, String size, String color);

    @Query("SELECT p FROM Product p JOIN p.category t JOIN p.sizes s WHERE s.name = :size AND t.name = :category AND p.price >= :min AND p.price <= :max")
    List<Product> getByFilterCategoryAndSizeAndPrice(String category, String size,BigDecimal min, BigDecimal max);

    @Query("SELECT p FROM Product p JOIN p.category t JOIN p.color c WHERE c.name = :color AND t.name = :category AND p.price >= :min AND p.price <= :max")
    List<Product> getByFilterCategoryAndColorAndPrice(String category, String color,BigDecimal min, BigDecimal max);

}
