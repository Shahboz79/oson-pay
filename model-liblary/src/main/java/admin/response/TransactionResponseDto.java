package admin.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponseDto {

    private long id;
    private String merchantName;
    private String gatewayMerchantName;
    private String gatewayName;
    private String agentName;
    private String agentMerchantName;
    private BigDecimal transactionAmount;
    private BigDecimal transactionGatewayAmount;
    private String transactionStatus;
    private Timestamp createdDate;
    private Timestamp updateDate;


}
