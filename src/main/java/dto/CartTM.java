package dto;

import lombok.*;

@AllArgsConstructor
@Data
@ToString
public class CartTM {
    private String orderID;
    private String custID;
    private String itemCode;
    private String description;
    private String unitPrice;
    private String qty;
    private String total;
}
