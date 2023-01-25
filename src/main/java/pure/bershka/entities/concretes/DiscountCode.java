package pure.bershka.entities.concretes;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "discount_codes")
public class DiscountCode {
	@Id
	@Column(name = "discount_code_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "discount_code")
	@NotBlank(message = "Discount code cannot be empty.")
	private String code;

	@Column(name = "discount_code_amount")
	@NotNull(message = "Discount code amount cannot be empty!")
	@PositiveOrZero(message = "Discount code amount  must be 0 or greater than 0!")
	private int amount;

	@Column(name = "discount_price")
	@NotNull(message = "Discount price amount cannot be empty!")
	@PositiveOrZero(message = "Discount price must be 0 or greater than 0!")
	private int price;

	@JsonIgnore
	@OneToMany(mappedBy = "discountCode", fetch = FetchType.LAZY)
	private List<Order> orders;

	public void setDiscountCode(DiscountCode discountCode){
		this.code = discountCode.getCode();
		this.amount = discountCode.getAmount();
		this.price = discountCode.getPrice();
	}
}
