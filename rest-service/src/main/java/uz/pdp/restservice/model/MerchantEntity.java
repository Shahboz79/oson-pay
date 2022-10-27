package uz.pdp.restservice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "db.merchant")
public class MerchantEntity extends BaseModelEntity {

    @OneToOne
    private GatewayMerchantEntity gatewayMerchantEntity;


}
