package admin.receive;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GatewayMerchantReceiveDto {
    private String name;
    @JsonProperty("gateway_id")
    private Long gatewayId;
}
