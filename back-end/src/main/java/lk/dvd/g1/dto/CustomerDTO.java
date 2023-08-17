package lk.dvd.g1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {
    private int id;
    private String name;
    private String address;
    private String contactNumber;

//    public CustomerDTO(String name, String address, String contactNumber) {
//        this.name = name;
//        this.address = address;
//        this.contactNumber = contactNumber;
//    }
}
