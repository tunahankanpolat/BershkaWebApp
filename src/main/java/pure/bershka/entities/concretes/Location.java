package pure.bershka.entities.concretes;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "locations")
public class Location {
    @Id
    @Column(name = "location_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "location_title")
    @NotBlank(message = "Title cannot be empty.")
    private String title;

    @Column(name = "address")
    @NotBlank(message = "Address cannot be empty.")
    private String address;

    @Column(name = "city")
    @NotBlank(message = "City cannot be empty.")
    private String city;

    @Column(name = "town")
    @NotBlank(message = "Town cannot be empty.")
    private String town;

    @Column(name = "post_code")
    @NotBlank(message = "Post code cannot be empty.")
    private String postCode;

    @JsonIgnore
    @OneToMany(mappedBy = "location", fetch = FetchType.LAZY)
    private List<Order> orders;

    @JsonIgnore
    @OneToMany(mappedBy = "billingLocation", fetch = FetchType.LAZY)
    private List<Order> billingLocations;

    @JsonIgnore
    @OneToMany(mappedBy = "lastLocation", fetch = FetchType.LAZY)
    private List<Customer> customersLastLocation;

    @JsonIgnore
    @ManyToMany(mappedBy = "locations", fetch = FetchType.LAZY)
    private List<Customer> customers;



    public Location(String title, String address, String city, String town, String postCode) {
        this.title = title;
        this.address = address;
        this.city = city;
        this.postCode = postCode;
        this.town = town;
    }

    public void setLocation(Location location){
        this.title = location.getTitle();
        this.city = location.getCity();
        this.address = location.getAddress();
        this.postCode = location.getPostCode();
        this.town = location.getTown();
    }

}
