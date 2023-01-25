package pure.bershka.entities.concretes;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pure.bershka.entities.dtos.ProductDto;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="products")

public class Product{
    @Id
    @Column(name="product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @NotBlank(message = "Product's name cannot be empty.")
    private String name;

    @Column(name = "price")
    @NotNull(message = "Product's price cannot be empty.")
    private BigDecimal price;

    @Column(name = "gender")
    @NotNull(message = "Gender must not be null!")
    private String gender;

    @Column(name = "discount_percentage")
    @PositiveOrZero(message = "Negative value cannot be accepted for discount percentage!")
    private BigDecimal discountPercentage;

    @ManyToOne
    @JoinColumn(name="category_id")
    @NotNull(message = "Category must be declared!")
    private Category category;

    @ManyToOne
    @JoinColumn(name="color_id")
    @NotNull(message = "Color must be declared!")
    private Color color;


    @ManyToOne
    @JoinColumn(name = "typology_id")
    @NotNull(message = "Typology must be declared!")
    private Typology typology;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name="product_sizes", joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "size_id"))
    private List<Size> sizes;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<Image> images;

    public void setProduct(Product product){
        this.name = product.getName();
        this.price = product.getPrice();
        this.gender = product.getGender();
        this.discountPercentage = product.getDiscountPercentage();
    }

    public Product(ProductDto productDto){
        this.price = productDto.getPrice();
        this.name = productDto.getName();
        if(productDto.getGender().equalsIgnoreCase("KADIN")) {
            this.gender = "FEMALE";
        }
        if(productDto.getGender().equalsIgnoreCase("ERKEK")) {
            this.gender = "MALE";
        }
        this.discountPercentage = productDto.getDiscountPercentage();
    }
}
