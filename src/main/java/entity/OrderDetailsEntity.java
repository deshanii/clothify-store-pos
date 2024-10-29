package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name ="orderDetails")
@Table(name ="orderDetails")
public class OrderDetailsEntity {
    @Id
    private String orderID;
    private LocalDate orderDate;
    private String custID;
}
