package pure.bershka.entities.concretes;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "sizes")
public class Size {
	@Id
	@Column(name = "size_id")
	private int id;

	@Column(name = "size_name")
	@NotBlank(message = "Size name cannot be empty.")
	private String name;

	@JsonIgnore
	@ManyToMany(mappedBy = "sizes", fetch = FetchType.LAZY)
	private List<Product> products;
}
