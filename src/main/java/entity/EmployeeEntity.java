package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity(name = "employee")
@Table(name = "employee")
public class EmployeeEntity {
    @Id
    private String empID;
    private String title;
    private String name;
    private String address;
    private String dob;
    private String contactNumber;
    private String bankAccNo;
    private String nic;

}
