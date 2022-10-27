package admin.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class GatewayMerchantResponse {

    private long id;
    @JsonProperty("gatewayEntity")
    private GatewayResponse gatewayEntity;
    private String name;



}
