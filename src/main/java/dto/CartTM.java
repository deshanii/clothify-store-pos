package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class CartTM {
    private String orderID;
    private String custID;
    private String itemCode;
    private String custName;
    private String description;
    private String unitPrice;
    private String qty;
    private Double total;

    public CartTM(String orderID, String custID, String itemCode, String description, Double unitPrice, Integer qty, Double total) {
    }
}
