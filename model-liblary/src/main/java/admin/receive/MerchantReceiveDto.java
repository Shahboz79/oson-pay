package admin.receive;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class MerchantReceiveDto {
    private String name;
    @JsonProperty("gateway_merchant_id")
    private Long gatewayMerchantId;
}
