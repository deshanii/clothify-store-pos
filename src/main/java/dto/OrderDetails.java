package dto;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class OrderDetails {
    private String orderID;
    private LocalDate orderDate;
    private String custID;

}
