package uz.pdp.restservice.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.restservice.model.GatewayEntity;
import uz.pdp.restservice.model.GatewayMerchantEntity;

import java.util.List;

public interface GatewayRepository extends JpaRepository<GatewayEntity, Long> {
    @Query(value = "select * from db_gateway where is_in_active=false", nativeQuery = true)
    List<GatewayEntity> findAll();

//    List<GatewayEntity> findAllByNameLikeIgnoreCase(String name, Pageable pageable);


    @Query(value = "SELECT * FROM db_gateway WHERE name LIKE ?1 and is_in_active = false offset (?2-1)*10 limit ?3",
            nativeQuery = true)
    List<GatewayEntity> getAllSearchResult(String name, int page, int size);
}

