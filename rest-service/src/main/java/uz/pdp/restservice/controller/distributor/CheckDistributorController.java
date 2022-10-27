package uz.pdp.restservice.controller.distributor;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.restservice.model.AgentEntity;
import uz.pdp.restservice.model.receive.DefaultCheckReceiveDto;
import uz.pdp.restservice.model.response.BaseCheckAgentResponse;
import uz.pdp.restservice.repository.AgentRepository;
import uz.pdp.restservice.service.distributor.CheckDistributor;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping("/api/transaction")
public class CheckDistributorController {

    private final AgentRepository agentRepository;
    private final CheckDistributor checkDistributor;

    public CheckDistributorController(AgentRepository agentRepository, CheckDistributor checkDistributor) {
        this.agentRepository = agentRepository;
        this.checkDistributor = checkDistributor;
    }

    @PostMapping("/check")
    public BaseCheckAgentResponse check(
            @RequestBody DefaultCheckReceiveDto defaultCheckReceiveDto,
            HttpServletRequest httpServletRequest
    ) throws IOException {

        AgentEntity agent = getAgent(httpServletRequest);
        checkDistributor.check(agent, defaultCheckReceiveDto);
        return null;
    }

    private AgentEntity getAgent(HttpServletRequest httpServletRequest) {
        String secretKey = httpServletRequest.getHeader("secret_key");
        return agentRepository.findBySecretKey(secretKey);
    }

}
