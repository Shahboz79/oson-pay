package uz.pdp.restservice.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.restservice.model.AgentEntity;
import uz.pdp.restservice.model.AgentMerchantEntity;
import uz.pdp.restservice.model.GatewayMerchantEntity;

import java.util.List;

public interface GatewayMerchantRepository extends JpaRepository<GatewayMerchantEntity, Long> {
    @Query(value = "select * from db_gateway_merchant where is_in_active=false ", nativeQuery = true)
    List<GatewayMerchantEntity> findAll();

//    List<GatewayMerchantEntity> findAllByNameLikeIgnoreCase(String name, Pageable pageable);


    @Query(value = "SELECT * FROM db_gateway_merchant WHERE name LIKE ?1 and is_in_active = false  offset (?2-1)*10 limit ?3",
            nativeQuery = true)
    List<GatewayMerchantEntity> getAllSearchResult(String name, int page, int size);

}
