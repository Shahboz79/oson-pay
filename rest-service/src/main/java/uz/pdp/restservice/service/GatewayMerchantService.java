package uz.pdp.restservice.service;

import admin.receive.GatewayMerchantReceiveDto;
import admin.response.ApiResponse;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import uz.pdp.restservice.model.GatewayEntity;
import uz.pdp.restservice.model.GatewayMerchantEntity;
import uz.pdp.restservice.repository.GatewayMerchantRepository;
import uz.pdp.restservice.repository.GatewayRepository;
import uz.pdp.restservice.service.base.BaseService;

import java.util.List;
import java.util.Optional;

@Service
public class GatewayMerchantService implements BaseService<GatewayMerchantReceiveDto, List<GatewayMerchantEntity>, GatewayMerchantEntity> {
    private final GatewayMerchantRepository gatewayMerchantRepository;
    private final GatewayRepository gatewayRepository;

    public GatewayMerchantService(GatewayMerchantRepository gatewayMerchantRepository, GatewayRepository gatewayRepository) {
        this.gatewayMerchantRepository = gatewayMerchantRepository;
        this.gatewayRepository = gatewayRepository;
    }

    @Override
    public ApiResponse<Object> add(GatewayMerchantReceiveDto gatewayMerchantReceiveDto) {
        Optional<GatewayEntity> optionalGateway = gatewayRepository.findById(gatewayMerchantReceiveDto.getGatewayId());
        if (optionalGateway.isEmpty()) {
            return new ApiResponse<>(400, "gateway not exists");
        }
        GatewayMerchantEntity gatewayMerchantEntity = new GatewayMerchantEntity();
        gatewayMerchantEntity.setGatewayEntity(optionalGateway.get());
        gatewayMerchantEntity.setName(gatewayMerchantReceiveDto.getName());
        gatewayMerchantRepository.save(gatewayMerchantEntity);
        return new ApiResponse<>(0, SUCCESS);
    }

    @Override
    public ApiResponse<List<GatewayMerchantEntity>> getList(int page) {
        Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("id").descending());
        return new ApiResponse<>(0, SUCCESS, gatewayMerchantRepository.findAll(pageable).stream().toList());
    }

    @Override
    public ApiResponse<List<GatewayMerchantEntity>> getList(int page, GatewayMerchantReceiveDto dto) {
        String name = "%" + (dto.getName() != null ? dto.getName() : "") + "%";
        System.out.println("name = " + name);
//        Pageable pageable = PageRequest.of(page - 1, 10);
//        List<GatewayMerchantEntity> findAll = gatewayMerchantRepository.findAllByNameLikeIgnoreCase(name, pageable);
        List<GatewayMerchantEntity> findAll = gatewayMerchantRepository.getAllSearchResult(name, page, 11);
        System.out.println("list size = " + findAll.size());
        return new ApiResponse<>(0, SUCCESS, findAll);
    }

    @Override
    public ApiResponse<List<GatewayMerchantEntity>> getAllList() {
        return new ApiResponse<>(0, SUCCESS, gatewayMerchantRepository.findAll());
    }

    @Override
    public GatewayMerchantEntity getById(long id) {
        return gatewayMerchantRepository.findById(id).get();
    }

    @Override
    public ApiResponse<Object> edit(GatewayMerchantReceiveDto gatewayMerchantReceiveDto, Long id) {
        Optional<GatewayEntity> optionalGateway = gatewayRepository.findById(gatewayMerchantReceiveDto.getGatewayId());
        GatewayMerchantEntity gatewayMerchantEntity = gatewayMerchantRepository.findById(id).get();
        gatewayMerchantEntity.setId(id);
        gatewayMerchantEntity.setName(gatewayMerchantReceiveDto.getName());
        gatewayMerchantEntity.setGatewayEntity(optionalGateway.get());
        gatewayMerchantRepository.save(gatewayMerchantEntity);
        return new ApiResponse<>(0, SUCCESS);
    }

    @Override
    public ApiResponse<Object> delete(long id) {
        GatewayMerchantEntity gatewayMerchantEntity = gatewayMerchantRepository.getReferenceById(id);
        gatewayMerchantEntity.setInActive(true);
        gatewayMerchantRepository.save(gatewayMerchantEntity);
        return new ApiResponse<>(0, SUCCESS);
    }

    @Override
    public boolean isNotNull(GatewayMerchantReceiveDto gatewayMerchantReceiveDto) {
        if (gatewayMerchantReceiveDto.getGatewayId() != null
                || gatewayMerchantReceiveDto.getName() != null) {
            return true;
        }
        return false;
    }
}
