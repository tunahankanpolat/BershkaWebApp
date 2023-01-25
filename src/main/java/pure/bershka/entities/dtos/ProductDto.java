package pure.bershka.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private String name;
    private BigDecimal price;
    private String gender;
    private BigDecimal discountPercentage;
    private int categoryId;
    private int colorId;
    private int typologyId;
    private String img_1;
    private String img_2;
    private String img_3;

    private int[] amount;
    private int[] sizeId;
}
