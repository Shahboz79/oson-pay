package uz.pdp.restservice.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.restservice.model.AgentEntity;
import uz.pdp.restservice.model.AgentMerchantEntity;

import java.util.List;
import java.util.Optional;

public interface AgentMerchantRepository extends JpaRepository<AgentMerchantEntity, Long> {
    @Query(value = "select * from db_agent_merchant_entity where is_in_active = false", nativeQuery = true)
    List<AgentMerchantEntity> findAll();

    Optional<AgentMerchantEntity> findByMerchantEntity_IdAndAgentEntity_Id(long merchantEntityId, long agentEntityId);

    @Query(value = "SELECT * FROM db_agent_merchant_entity WHERE name LIKE ?1 " +
            " and CAST(min_sum AS varchar) LIKE ?2  and CAST(max_sum AS varchar) LIKE ?3 " +
            " and is_in_active = false offset (?4-1)*10 limit ?5",
            nativeQuery = true)
    List<AgentMerchantEntity> getAllSearchResult(String name, String min, String max, int page, int size);

}
