package pure.bershka.entities.dtos;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {

    private String email;

    private String password;
    
    private String firstName;

    private String lastName;
    
    private String identityNumber;
    
    private String address;
    
    private String addressTitle;
    
    private String city;

    private String town;
    
    private String postalCode;

    private String phoneNumber;
    
    private boolean acceptRuleKVKK;
    
    private boolean acceptRuleInfo;

	
}
