package uz.pdp.restservice.service;

import admin.receive.GatewayReceiveDto;
import admin.response.ApiResponse;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import uz.pdp.restservice.model.GatewayEntity;
import uz.pdp.restservice.repository.GatewayRepository;
import uz.pdp.restservice.service.base.BaseService;

import java.util.List;

@Service
public class GatewayService implements BaseService<GatewayReceiveDto, List<GatewayEntity>, GatewayEntity> {

    private final GatewayRepository gatewayRepository;

    public GatewayService(GatewayRepository gatewayRepository) {
        this.gatewayRepository = gatewayRepository;
    }

    @Override
    public ApiResponse<Object> add(GatewayReceiveDto gatewayReceiveDto) {
        GatewayEntity gatewayEntity = new GatewayEntity();
        gatewayEntity.setName(gatewayReceiveDto.getName());
        gatewayRepository.save(gatewayEntity);
        return new ApiResponse<>(0, SUCCESS);
    }

    @Override
    public ApiResponse<List<GatewayEntity>> getList(int page) {
        Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("id").descending());
        return new ApiResponse<>(0, SUCCESS, gatewayRepository.findAll(pageable).stream().toList());
    }

    @Override
    public ApiResponse<List<GatewayEntity>> getList(int page, GatewayReceiveDto dto) {
        String name = "%" + (dto.getName() != null ? dto.getName() : "") + "%";
//        Pageable pageable = PageRequest.of(page - 1, 10);
//        List<GatewayEntity> findAll = gatewayRepository.findAllByNameLikeIgnoreCase(name, pageable);
        List<GatewayEntity> findAll = gatewayRepository.getAllSearchResult(name, page, 11);
        System.out.println("list = " + findAll);
        return new ApiResponse<>(0, SUCCESS, findAll);
    }

    @Override
    public ApiResponse<List<GatewayEntity>> getAllList() {
        return new ApiResponse<>(0, SUCCESS, gatewayRepository.findAll());
    }

    @Override
    public GatewayEntity getById(long id) {
        return gatewayRepository.findById(id).get();
    }

    @Override
    public ApiResponse<Object> edit(GatewayReceiveDto gatewayReceiveDto, Long id) {
        GatewayEntity gateway = gatewayRepository.findById(id).get();
        gateway.setId(id);
        gateway.setName(gatewayReceiveDto.getName());
        gatewayRepository.save(gateway);
        return new ApiResponse<>(0, SUCCESS);
    }

    @Override
    public ApiResponse<Object> delete(long id) {
        GatewayEntity gatewayEntity = gatewayRepository.getReferenceById(id);
        gatewayEntity.setInActive(true);
        gatewayRepository.save(gatewayEntity);
        return new ApiResponse<>(0, SUCCESS);
    }

    @Override
    public boolean isNotNull(GatewayReceiveDto gatewayReceiveDto) {
        if (gatewayReceiveDto.getName() != null) {
            return true;
        }
        return false;
    }
}
