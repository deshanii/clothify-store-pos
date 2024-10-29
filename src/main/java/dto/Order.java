package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class Order {
    private String orderID;
    private String custID;
    private String itemCode;
//    private String custName;
    private String description;
    private String unitPrice;
    private String qty;
//    private String stock;

}
