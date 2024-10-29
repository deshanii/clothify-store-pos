package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
@Data
@AllArgsConstructor
@Entity(name = "order_table")
public class OrderEntity {
    @Id
    private String orderID;
    private String custID;
    private String itemCode;
    private String description;
    private String unitPrice;
    private String qty;
    private String total;
}
