package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Table(name = "item")
@Entity(name = "item")
public class ItemEntity {
    @Id
    private String itemCode;
    private String supplierID;
    private String description;
    private String packSize;
    private Double unitPrice;
    private Integer qtyOnHand;
}
