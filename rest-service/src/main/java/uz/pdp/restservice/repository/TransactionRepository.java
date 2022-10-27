package uz.pdp.restservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.restservice.model.AgentEntity;
import uz.pdp.restservice.model.AgentMerchantEntity;
import uz.pdp.restservice.model.TransactionEntity;

import java.util.List;

public interface TransactionRepository extends JpaRepository<TransactionEntity,Long> {


    @Query(value = "SELECT * FROM db_transaction order by id desc offset (?1-1)*10 limit ?2", nativeQuery = true)
    List<TransactionEntity> getAllResult(int page, int size);

    @Query(value = "SELECT * FROM db_transaction WHERE state LIKE ?1 " +
            " and CAST(agent_merchant_entity_id AS varchar) LIKE ?2 " +
            " and CAST(merchant_entity_id AS varchar) LIKE ?3 " +
            " order by id desc offset (?4-1)*10 limit ?5",
            nativeQuery = true)
    List<TransactionEntity> getAllSearchResult(
            String state,
            String agent_merchant_entity_id,
            String merchant_entity_id,
            int page, int size
    );
}
