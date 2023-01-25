package pure.bershka.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@Table(name="users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Column(name = "first_name")
    @NotBlank(message = "First name cannot be empty!")
    private String firstName;

    @Column(name = "last_name")
    @NotBlank(message = "Last name cannot be empty!")
    private String lastName;

    @Column(name = "email", unique = true)
    @Email(message = "Email format is not valid!")
    @NotBlank(message = "Email cannot be empty!")
    private String email;

    @Column(name = "password")
    @NotBlank(message = "Password cannot be empty!")
    @Size(min = 6, max = 15,message = "Password must be minimum 6, maximum 15 length!")
    private String password;

    @Column(name = "date_of_register")
    @NotBlank(message = "Date of register must be given.")
    private LocalDateTime addDate;

}
