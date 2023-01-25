package pure.bershka.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="stocks")
public class Stock {
    @Id
    @Column(name = "stock_id")
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

    @Column(name = "count")
    @Min(value = 0,message = "Mininum stock amount for a product can be 0.")
    @NotNull(message = "Amount for a product cannot be empty.")
    private int count;

    public void setStock(Stock stock){
        this.count = stock.getCount();
    }
    public void decreaseCount(int value){
        this.count = this.getCount() - value;
    }

    public Stock(Product product, Size size, int count) {
        this.product = product;
        this.size = size;
        this.count = count;
    }
}
