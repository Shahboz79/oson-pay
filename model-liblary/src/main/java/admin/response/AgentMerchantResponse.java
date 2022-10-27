package admin.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class AgentMerchantResponse {

    private long id;
    private String name;
    private double minSum;
    private double maxSum;

    @JsonProperty("agentEntity")
    private AgentResponse agentEntity;

    @JsonProperty("merchantEntity")
    private MerchantResponse merchantEntity;


}
