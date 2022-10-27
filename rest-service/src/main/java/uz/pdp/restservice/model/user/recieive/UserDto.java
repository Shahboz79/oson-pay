package uz.pdp.restservice.model.user.recieive;

import lombok.*;

import java.util.List;

@Getter
@Setter
public class UserDto {
    private String name;
    private String username;
    private String password;
    private String role;
    private List<String> permissions;

}
