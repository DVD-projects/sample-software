package lk.dvd.g1.api;

import lk.dvd.g1.dto.CustomerDTO;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1/customer")
public class CustomerController {
    @Autowired
    private BasicDataSource pool;

    @GetMapping
    public ResponseEntity<?> getCustomer(@RequestParam(value = "q",required = false) String query) {
        System.out.println(query);
        if (query==null) query="";
        query = "%" + query + "%";
        try(Connection connection = pool.getConnection()){
            PreparedStatement stm = connection.prepareStatement("SELECT * FROM customer WHERE id LIKE ?  OR name LIKE ? OR address LIKE ? OR contact LIKE ?");
            for (int i = 1; i <=4; i++) {
                stm.setString(i,query);
            }
            ResultSet rs = stm.executeQuery();
            List<CustomerDTO> customerList = new ArrayList<>();
            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String address = rs.getString("address");
                String contactNumber = rs.getString("contact");
                CustomerDTO customer = new CustomerDTO(id, name, address, contactNumber);
                customerList.add(customer);
            }
            return new ResponseEntity<>(customerList,HttpStatus.OK);
        }catch (SQLException e){
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

   
}
