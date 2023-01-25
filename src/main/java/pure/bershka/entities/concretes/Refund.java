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
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="refunds")
public class Refund {
    @Id
    @Column(name = "refund_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "cause")
    private String cause;       // bu eleman getRefunds kısmında hata verdiriyor.

    @Column(name = "refund_date")
    @NotNull(message = "Refund date must be declared.")
    private LocalDateTime refundDate;

    @Column(name = "is_online")
    @NotNull(message = "Must be declared as default 'true'.")
    private boolean isOnline;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "customer_id")
    @NotNull(message = "Customer must be given.")
    private Customer customer;

    @OneToOne
    @JoinColumn(name = "order_detail_id")
    @NotNull(message = "Detail of the ordered product must be given.")
    private OrderDetail orderDetail;

    public Refund(String cause, LocalDateTime refundDate, boolean isOnline, Customer customer, OrderDetail orderDetail) {
        this.cause = cause;
        this.refundDate = refundDate;
        this.isOnline = isOnline;
        this.customer = customer;
        this.orderDetail = orderDetail;
    }
}
