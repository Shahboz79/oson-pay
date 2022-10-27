package uz.pdp.restservice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "db.gateway_merchant")
public class GatewayMerchantEntity extends BaseModelEntity {

    @ManyToOne
    private GatewayEntity gatewayEntity;
}
