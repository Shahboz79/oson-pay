package uz.pdp.restservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.restservice.model.user.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);

    @Query(value = "select * from user_entity where permissions IS NULL order by name offset (?1-1)*5 limit ?2",
            nativeQuery = true)
    List<UserEntity> getCustomersList(int page, int size);

    @Query(value = "select * from user_entity where  is_blocked = false and username = ?1",
            nativeQuery = true)
    UserEntity getCustomer(String username);
}
