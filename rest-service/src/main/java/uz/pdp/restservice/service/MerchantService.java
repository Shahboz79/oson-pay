package uz.pdp.restservice.service;

import admin.receive.MerchantReceiveDto;
import admin.response.ApiResponse;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import uz.pdp.restservice.model.MerchantEntity;
import uz.pdp.restservice.repository.GatewayMerchantRepository;
import uz.pdp.restservice.repository.MerchantRepository;
import uz.pdp.restservice.service.base.BaseService;

import java.util.List;

@Service
public class MerchantService implements BaseService<MerchantReceiveDto, List<MerchantEntity>, MerchantEntity> {
    private final MerchantRepository merchantRepository;
    private final GatewayMerchantRepository gatewayMerchantRepository;

    public MerchantService(MerchantRepository merchantRepository, GatewayMerchantRepository gatewayMerchantRepository) {
        this.merchantRepository = merchantRepository;
        this.gatewayMerchantRepository = gatewayMerchantRepository;
    }

    @Override
    public ApiResponse<Object> add(MerchantReceiveDto merchantReceiveDto) {
        MerchantEntity merchantEntity = new MerchantEntity();
        merchantEntity.setName(merchantReceiveDto.getName());
        merchantEntity.setGatewayMerchantEntity(gatewayMerchantRepository.findById(merchantReceiveDto.getGatewayMerchantId()).get());
        merchantRepository.save(merchantEntity);
        return new ApiResponse<>(0, SUCCESS);
    }

    @Override
    public ApiResponse<List<MerchantEntity>> getList(int page) {
        Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("id").descending());
        return new ApiResponse<>(0, SUCCESS, merchantRepository.findAll(pageable).stream().toList());
    }

    @Override
    public ApiResponse<List<MerchantEntity>> getList(int page, MerchantReceiveDto dto) {
        String name = "%" + (dto.getName() != null ? dto.getName() : "") + "%";
//        Pageable pageable = PageRequest.of(page - 1, 10);
//        List<MerchantEntity> findAll = merchantRepository.findAllByNameLikeIgnoreCase(name, pageable);
        List<MerchantEntity> findAll = merchantRepository.getAllSearchResult(name, page, 11);
        System.out.println("list size = " + findAll.size());
        return new ApiResponse<>(0, SUCCESS, findAll);
    }

    @Override
    public ApiResponse<List<MerchantEntity>> getAllList() {
        return new ApiResponse<>(0, SUCCESS, merchantRepository.findAll());
    }

    @Override
    public MerchantEntity getById(long id) {
        return merchantRepository.getReferenceById(id);
    }

    @Override
    public ApiResponse<Object> edit(MerchantReceiveDto merchantReceiveDto, Long id) {
        return null;
    }

    @Override
    public ApiResponse<Object> delete(long id) {
        MerchantEntity merchantEntity = merchantRepository.getReferenceById(id);
        merchantEntity.setInActive(true);
        merchantRepository.save(merchantEntity);
        return new ApiResponse<>(0, SUCCESS);
    }

    @Override
    public boolean isNotNull(MerchantReceiveDto merchantReceiveDto) {
        if (merchantReceiveDto.getName() != null || merchantReceiveDto.getGatewayMerchantId() != null) {
            return true;
        }
        return false;
    }
}
