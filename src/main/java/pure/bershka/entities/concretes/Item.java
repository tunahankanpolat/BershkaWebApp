package pure.bershka.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="items")
@Getter
@Setter
public class Item {
    @Id
    @Column(name = "item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @NotNull(message = "Product id must be declared.")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "size_id")
    @NotNull(message = "Size id must be declared.")
    private Size size;

    @Column(name = "amount")
    @Min(value = 0,message = "Mininum choosen amount for a product can be 0.")
    @NotNull(message = "Amount for a product cannot be empty.")
    private int amount;

    public Item(Product product, Size size, int amount) {
        this.product = product;
        this.size = size;
        this.amount = amount;
    }
}
