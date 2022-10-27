package uz.pdp.restservice.model.user;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "role_entity")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "authority")
    private String authority;

    public RoleEntity() {
    }

    public RoleEntity(String roleEnum) {
        this.authority = roleEnum;
    }
}
