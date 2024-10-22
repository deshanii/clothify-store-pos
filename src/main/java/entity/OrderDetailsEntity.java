package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@NoArgsConstructor
@Setter
@Getter
@Entity(name ="orderDetails")
@Table(name ="orderDetails")
public class OrderDetailsEntity {
    @Id
    private String orderID;
    private String oredrDate;
    private String custID;
}
