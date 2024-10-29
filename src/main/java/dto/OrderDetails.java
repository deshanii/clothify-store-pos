package dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class OrderDetails {
    private String orderID;
    private String orderDate;
    private String custID;

}
