package dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Item {
    private String itemCode;
    private String supplierID;
    private String description;
    private String packSize;
    private String unitPrice;
    private String qtyOnHand;

}
