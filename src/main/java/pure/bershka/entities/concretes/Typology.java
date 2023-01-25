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
import javax.validation.constraints.NotNull;

import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="typologies")
public class Typology {
    @Id
    @Column(name = "typology_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "typology_name")
    @NotBlank(message = "Typology name must not be blank!")
    private String name;

    @Column(name = "gender")
    @NotNull(message = "Gender must not be null!")
    private String gender;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "category_id")
    @NotNull(message = "Category id must be given.")
    private Category category;

    @JsonIgnore
    @OneToMany(mappedBy = "typology", fetch = FetchType.LAZY)
    private List<Product> products;

    public void setTypology(Typology typology){
        this.name = typology.getName();
        this.category = typology.getCategory();
        this.gender = typology.getGender();
    }
}
