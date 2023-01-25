package pure.bershka.entities.concretes;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "total_price")
    @NotNull(message = "Total order price cannot be empty.")
    @Min(value = 0,message = "Total order price cannot be 0.")
    private int totalPrice;

    @Column(name = "creation_date")
    @NotNull(message = "Creation date cannot be empty.")
    private LocalDateTime creationDate;

    @Column(name = "cancel_date")
    private LocalDateTime cancelDate;


    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "customer_id")
    @NotNull(message = "Customer must be given.")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "location_id")
    @NotNull(message = "Location cannot be empty.")
    private Location location;

    @ManyToOne
    @JoinColumn(name = "billing_location_id")
    @NotNull(message = "Billing Location cannot be empty.")
    private Location billingLocation;

    @Column(name = "is_canceled")
    @NotNull(message = "To check this product's cancel status, it should be given false as default.")
    private boolean isCanceled;

    @ManyToOne
    @JoinColumn(name = "discount_code_id")
    @NotNull(message = "Discount code cannot be empty.")
    private DiscountCode discountCode;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    private List<OrderDetail> orderDetails;

    @JsonIgnore
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<OrderDetail> products;

    @JsonIgnore
    @OneToMany(mappedBy = "size", fetch = FetchType.LAZY)
    private List<OrderDetail> sizes;

    public Order(int totalPrice, LocalDateTime creationDate, LocalDateTime cancelDate, Customer customer,
                 Location location, Location billingLocation, DiscountCode
                         discountCode, boolean isCanceled) {
        this.totalPrice = totalPrice;
        this.creationDate = creationDate;
        this.cancelDate = cancelDate;
        this.customer = customer;
        this.location = location;
        this.billingLocation = billingLocation;
        this.discountCode = discountCode;
        this.isCanceled = isCanceled;
    }

//    public Order(Date creationDate, int totalPrice,
//                 int customerId, int locationId, int billingLocationId, int discountCodeId, List<OrderDetail> orderDetails,){
//        this.creationDate = creationDate;
//        this.totalPrice = totalPrice;
//        this.billingLocation.setId(billingLocationId);
//        this.customer.setId(customerId);
//        this.discountCode.setId(discountCodeId);
//        this.location.setId(locationId);
//
//    }
    public void setOrder(Order order){
        this.location = order.getLocation();
    }
}
