package uz.pdp.restservice.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.restservice.model.AgentEntity;

import java.util.List;

public interface AgentRepository extends JpaRepository<AgentEntity, Long> {

    AgentEntity findBySecretKey(String secretKey);

    @Query(value = "select * from db_agent a where a.is_in_active = false ", nativeQuery = true)
    List<AgentEntity> findAll();

//    List<AgentEntity> findByNameLikeIgnoreCase(String name, Pageable pageable);


    @Query(value = "SELECT * FROM db_agent WHERE name LIKE ?1 and is_in_active = false order by id offset (?2-1)*10 limit ?3",
            nativeQuery = true)
    List<AgentEntity> getAllSearchResult(String name, int page, int size);
}
