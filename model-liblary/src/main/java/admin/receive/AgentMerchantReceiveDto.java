package admin.receive;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class AgentMerchantReceiveDto {
    private String name;
    @JsonProperty("min_sum")
    private Double minSum;
    @JsonProperty("max_sum")
    private Double maxSum;
    private Double commission;

    private Long agentId;
    private Long merchantId;
}
