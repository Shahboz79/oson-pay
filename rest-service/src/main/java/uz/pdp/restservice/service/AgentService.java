package uz.pdp.restservice.service;

import admin.receive.AgentReceiveDto;
import admin.response.ApiResponse;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import uz.pdp.restservice.model.AgentEntity;
import uz.pdp.restservice.repository.AgentRepository;
import uz.pdp.restservice.service.base.BaseService;

import java.util.List;

@Service
public class AgentService implements BaseService<AgentReceiveDto, List<AgentEntity>, AgentEntity> {
    private final AgentRepository agentRepository;

    public AgentService(AgentRepository agentRepository) {
        this.agentRepository = agentRepository;
    }

    @Override
    public ApiResponse<Object> add(AgentReceiveDto agentReceiveDto) {
        AgentEntity agentEntity = new AgentEntity();
        agentEntity.setName(agentReceiveDto.getName());
        agentEntity.setSecretKey(agentReceiveDto.getSecretKey());
        agentRepository.save(agentEntity);
        return new ApiResponse<>(0, SUCCESS);
    }

    @Override
    public ApiResponse<List<AgentEntity>> getList(int page) {
        Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("id").descending());
        return new ApiResponse<>(0, SUCCESS, agentRepository.findAll(pageable).stream().toList());
    }

    @Override
    public ApiResponse<List<AgentEntity>> getList(int page, AgentReceiveDto dto) {
        String name = "%" + (dto.getName() != null ? dto.getName() : "") + "%";
//        Pageable pageable = PageRequest.of(page - 1, 10);
//        List<AgentEntity> findAll = agentRepository.findByNameLikeIgnoreCase(name, pageable);
        List<AgentEntity> findAll = agentRepository.getAllSearchResult(name, page, 11);
        System.out.println("list size = " + findAll.size());
        return new ApiResponse<>(0, SUCCESS, findAll);
    }

    @Override
    public ApiResponse<List<AgentEntity>> getAllList() {
        return new ApiResponse<>(0, SUCCESS, agentRepository.findAll());
    }

    @Override
    public AgentEntity getById(long id) {
        return agentRepository.findById(id).get();
    }

    @Override
    public ApiResponse<Object> edit(AgentReceiveDto agentReceiveDto, Long id) {
        AgentEntity agent = agentRepository.findById(id).get();
        agent.setId(id);
        agent.setName(agentReceiveDto.getName());
        agent.setSecretKey(agentReceiveDto.getSecretKey());
        agentRepository.save(agent);
        return new ApiResponse<>(0, SUCCESS);
    }

    @Override
    public ApiResponse<Object> delete(long id) {
        AgentEntity agentEntity = agentRepository.getReferenceById(id);
        agentEntity.setInActive(true);
        agentRepository.save(agentEntity);
        return new ApiResponse<>(0, SUCCESS);
    }

    @Override
    public boolean isNotNull(AgentReceiveDto dto) {
        if (dto.getName() != null) {
            return true;
        }
        return false;
    }
}
