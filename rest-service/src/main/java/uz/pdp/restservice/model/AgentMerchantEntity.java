package uz.pdp.restservice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "db.agent_merchant_entity")
public class AgentMerchantEntity extends BaseModelEntity {

    @ManyToOne
    private AgentEntity agentEntity;

    @ManyToOne
    private MerchantEntity merchantEntity;

    @Column(name = "min_sum")
    private double minSum;

    @Column(name = "max_sum")
    private double maxSum;
    private double commission;

}
