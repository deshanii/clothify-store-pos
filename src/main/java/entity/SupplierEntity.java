package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@ToString
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "supplier")
@Table(name = "supplier")

public class SupplierEntity {
    @Id
    private String supplierID;
    private String title;
    private String name;
    private String contactNumber;
    private String companyName;
    private String email;


}
