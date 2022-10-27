package uz.pdp.restservice.model.receive;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(as = DefaultCheckReceiveDto.class)
public class DefaultCheckReceiveDto{

    @JsonProperty("account")
    private String transactionAccount;

    private BigDecimal amount;
    @JsonProperty("merchant_id")
    private long merchantId;

    @JsonIgnore
    public boolean isFull() {
        return transactionAccount != null && merchantId != 0 && amount != null;
    }

}
