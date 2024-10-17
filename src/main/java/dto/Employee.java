package dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Employee {
    private String empID;
    private String title;
    private String name;
    private String address;
    private String dob;
    private String contactNumber;
    private String bankAccNo;
    private String nic;

}
