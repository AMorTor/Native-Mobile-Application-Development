package mx.ipn.crud.Model.DTO;

import lombok.Data;

@Data
public class UserDTO {
    private Integer id;
    private String username;
    private String lastname;
    private String email;
    private String role;
}