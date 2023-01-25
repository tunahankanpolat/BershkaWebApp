package pure.bershka.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Entity
@Table(name = "products_view")
@Immutable
public class ProductView {
    @Id
    private int productId;

    private String name;

    private BigDecimal price;

    private String gender;

    private BigDecimal discountPercentage;

    private String categoryName;

    private String colorName;

    private String typologyName;
}
