package uz.pdp.restservice.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "db.transaction")
public class TransactionEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private BigDecimal transactionAmount;

    private BigDecimal transactionGatewayAmount;

    @OneToOne
    private MerchantEntity merchantEntity;

    @OneToOne
    private AgentMerchantEntity agentMerchantEntity;

    @Enumerated(EnumType.STRING)
    private TransactionState state;

    @CreationTimestamp
    private Timestamp createdDate;

    @CreationTimestamp
    private Timestamp updatedDate;

    public TransactionEntity() {
    }

    public TransactionEntity(TransactionState state) {
        this.state = state;
    }

}
