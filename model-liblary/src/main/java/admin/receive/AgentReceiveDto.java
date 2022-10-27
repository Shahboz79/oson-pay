package admin.receive;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AgentReceiveDto {
    private String name;
    @JsonProperty(value = "secret_key")
    private String secretKey;
}
