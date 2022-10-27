package uz.pdp.restservice.service;

import admin.receive.AgentMerchantReceiveDto;
import admin.response.ApiResponse;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import uz.pdp.restservice.model.AgentEntity;
import uz.pdp.restservice.model.AgentMerchantEntity;
import uz.pdp.restservice.model.MerchantEntity;
import uz.pdp.restservice.repository.AgentMerchantRepository;
import uz.pdp.restservice.repository.AgentRepository;
import uz.pdp.restservice.repository.MerchantRepository;
import uz.pdp.restservice.service.base.BaseService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AgentMerchantService implements BaseService<AgentMerchantReceiveDto, List<AgentMerchantEntity>, AgentMerchantEntity> {

    private final AgentMerchantRepository agentMerchantRepository;
    private final AgentRepository agentRepository;
    private final MerchantRepository merchantRepository;

    public AgentMerchantService(AgentMerchantRepository agentMerchantRepository, AgentRepository agentRepository, MerchantRepository merchantRepository, MerchantRepository merchantRepository1) {
        this.agentMerchantRepository = agentMerchantRepository;
        this.agentRepository = agentRepository;
        this.merchantRepository = merchantRepository1;
    }

    @Override
    public ApiResponse<Object> add(AgentMerchantReceiveDto agentMerchantReceiveDto) {
        Optional<AgentEntity> agent = agentRepository.findById(agentMerchantReceiveDto.getAgentId());
        MerchantEntity merchantEntity = merchantRepository.findById(agentMerchantReceiveDto.getMerchantId()).get();
        AgentMerchantEntity agentMerchantEntity = new AgentMerchantEntity();
        agentMerchantEntity.setName(agentMerchantReceiveDto.getName());
        agentMerchantEntity.setMinSum(agentMerchantReceiveDto.getMinSum());
        agentMerchantEntity.setMaxSum(agentMerchantReceiveDto.getMaxSum());
        agentMerchantEntity.setAgentEntity(agent.get());
        agentMerchantEntity.setMerchantEntity(merchantEntity);
        agentMerchantRepository.save(agentMerchantEntity);
        return new ApiResponse<>(0, SUCCESS);
    }

    @Override
    public ApiResponse<List<AgentMerchantEntity>> getList(int page) {
        Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("id").descending());
        return new ApiResponse<>(0, SUCCESS, agentMerchantRepository.findAll(pageable).stream().toList());
    }

    @Override
    public ApiResponse<List<AgentMerchantEntity>> getList(int page, AgentMerchantReceiveDto dto) {
        String name = "%" + (dto.getName() != null ? dto.getName() : "") + "%";
        String minSum = "%" + (dto.getMinSum() != null ? dto.getMinSum().toString().substring(0, dto.getMinSum().toString().indexOf(".")) : "") + "%";
        String maxSum = "%" + (dto.getMaxSum() != null ? dto.getMaxSum().toString().substring(0, dto.getMaxSum().toString().indexOf(".")) : "") + "%";

        List<AgentMerchantEntity> findAll = agentMerchantRepository.getAllSearchResult(name, minSum, maxSum, page, 11);
        System.out.println("list size = " + findAll.size());
        return new ApiResponse<>(0, SUCCESS, findAll);
    }

    @Override
    public ApiResponse<List<AgentMerchantEntity>> getAllList() {
        return new ApiResponse<>(0, SUCCESS, agentMerchantRepository.findAll());
    }

    @Override
    public AgentMerchantEntity getById(long id) {
        return agentMerchantRepository.findById(id).get();
    }

    @Override
    public ApiResponse<Object> edit(AgentMerchantReceiveDto agentMerchantReceiveDto, Long id) {
        Optional<AgentEntity> optionalAgent = agentRepository.findById(agentMerchantReceiveDto.getAgentId());
        AgentMerchantEntity agentMerchantEntity = agentMerchantRepository.findById(id).get();
        agentMerchantEntity.setId(id);
        agentMerchantEntity.setName(agentMerchantReceiveDto.getName());
        agentMerchantEntity.setMaxSum(agentMerchantReceiveDto.getMaxSum());
        agentMerchantEntity.setMinSum(agentMerchantReceiveDto.getMinSum());
        agentMerchantEntity.setAgentEntity(optionalAgent.get());
        agentMerchantRepository.save(agentMerchantEntity);
        return new ApiResponse<>(0, SUCCESS);
    }

    @Override
    public ApiResponse<Object> delete(long id) {
        AgentMerchantEntity agentMerchantEntity = agentMerchantRepository.findById(id).get();
        agentMerchantEntity.setInActive(true);
        agentMerchantRepository.save(agentMerchantEntity);
        return new ApiResponse<>(0, SUCCESS);
    }

    @Override
    public boolean isNotNull(AgentMerchantReceiveDto agentMerchantReceiveDto) {
        if (agentMerchantReceiveDto.getName() != null
                || agentMerchantReceiveDto.getMinSum() != null
                || agentMerchantReceiveDto.getMaxSum() != null) {
            return true;
        }
        return false;
    }
}
