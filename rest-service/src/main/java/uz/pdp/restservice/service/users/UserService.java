package uz.pdp.restservice.service.users;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.restservice.model.enums.Role;
import uz.pdp.restservice.model.user.RoleEntity;
import uz.pdp.restservice.model.user.UserEntity;
import uz.pdp.restservice.model.user.recieive.UserDto;
import uz.pdp.restservice.repository.RoleRepository;
import uz.pdp.restservice.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ObjectMapper objectMapper;

    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository, RoleRepository roleRepository, ObjectMapper objectMapper) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.objectMapper = objectMapper;
    }


    public boolean addAdmin(
            final Optional<UserDto> userDto
    ) throws JsonProcessingException {
        if (userRepository.findByUsername(userDto.orElseThrow().getUsername()).isPresent()) {
            return false;
        }
        Optional<RoleEntity> optionalRoleEntity = roleRepository.findByAuthority(userDto.get().getRole());
        UserEntity user = new UserEntity();

        user.setName(userDto.get().getName());
        user.setUsername(userDto.get().getUsername());
        user.setPassword(passwordEncoder.encode(userDto.get().getPassword()));
        user.setRoleEntity(List.of(optionalRoleEntity.orElseThrow()));
        user.setPermissions(objectMapper.writeValueAsString(userDto.get().getPermissions()));
        userRepository.save(user);

        return true;
    }


    public void addSuperAdmin(){
        if (userRepository.findByUsername("admin").isPresent()){
            return;
        }
        roleRepository.save(new RoleEntity("SUPER_ADMIN"));
        roleRepository.save(new RoleEntity("ADMIN"));

        UserEntity admin = new UserEntity();
        admin.setName("SuperAdmin");
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("admin"));
        admin.setRoleEntity(List.of(roleRepository.findByAuthority(Role.SUPER_ADMIN.name()).get()));
        userRepository.save(admin);
        System.out.println("Successfully Added SuperAdmin");
    }

}
