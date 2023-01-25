package pure.bershka.entities.concretes;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.*;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "customers")
public class Customer extends User {
    @Id
    @Column(name = "customer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "budget")
    @NotNull
    @Min(value = 0, message = "Budget must be minimum 0!")
    private int budget;

    @Column(name = "identity_number")
    @NotBlank
    @Size(min = 10, max = 11,message = "Identity number must be minimum 10, maximum 11 length!")
    @Pattern(regexp = "^[0-9-]*$",message = "Identity number must contain only numbers!")
    private String identityNumber;

    @Column(name = "phone_number")
    @NotNull
    @Pattern(regexp = "^[0-9]*$",message = "Phone number must contain only numbers and '-' symbol!")
    private String phoneNumber;
    
    @Column(name = "is_active")
    private boolean isActive;
    
    @ManyToOne
    @JoinColumn(name = "last_location_id")
    @NotNull
    private Location lastLocation;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private List<Order> orders;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private List<Refund> refunds;

    @ManyToMany
    @JoinTable(name="customer_locations", joinColumns = @JoinColumn(name = "customer_id"),
                                    inverseJoinColumns = @JoinColumn(name="location_id"))
    private List<Location> locations;

    @ManyToMany
    @JoinTable(name="baskets", joinColumns = @JoinColumn(name = "customer_id"),
                                    inverseJoinColumns = @JoinColumn(name = "item_id"))
    private List<Item> basket;

    @ManyToMany
    @JoinTable(name="favorites", joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> favorites;

    public void setCustomer(Customer customer){
        this.phoneNumber = customer.getPhoneNumber();
        this.setFirstName(customer.getFirstName());
        this.phoneNumber = customer.getPhoneNumber();
        this.setEmail(customer.getEmail());
        this.setPassword(customer.getPassword());
    }
}
