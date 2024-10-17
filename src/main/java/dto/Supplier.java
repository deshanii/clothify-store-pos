package dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Supplier {
    private String supplierID;
    private String title;
    private String name;
    private String contactNumber;
    private String companyName;
    private String email;
}
