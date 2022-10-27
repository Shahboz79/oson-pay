package uz.pdp.restservice.controller;

import admin.receive.AgentReceiveDto;
import admin.receive.GatewayMerchantReceiveDto;
import admin.response.ApiResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import uz.pdp.restservice.service.base.BaseService;

import java.util.Optional;

import static uz.pdp.restservice.service.base.ResponseMessage.ID_EMPTY;
import static uz.pdp.restservice.service.base.ResponseMessage.SUCCESS;

@RestController
@RequestMapping("/api/gateway/merchant")
public class GatewayMerchantController {

    private final BaseService baseService;

    public GatewayMerchantController(@Qualifier("gatewayMerchantService") BaseService baseService) {
        this.baseService = baseService;
    }

    @PostMapping("/add")
    public ApiResponse add(@RequestBody GatewayMerchantReceiveDto gatewayMerchantReceiveDto) {
        return baseService.add(gatewayMerchantReceiveDto);
    }

    @PostMapping("/list/{page}")
    public ApiResponse getList(
            @PathVariable int page,
            @RequestBody GatewayMerchantReceiveDto gatewayMerchantReceiveDto
    ) {
        return baseService.getList(page, gatewayMerchantReceiveDto);
    }

    @GetMapping("/list/all")
    public ApiResponse getAllList() {
        return baseService.getAllList();
    }

    @GetMapping("/get/{id}")
    public ApiResponse get(@PathVariable long id) {
        return new ApiResponse<>(0, SUCCESS, baseService.getById(id));
    }

    @PostMapping("/edit/{id}")
    public ApiResponse edit(@PathVariable long id, @RequestBody GatewayMerchantReceiveDto gatewayMerchantReceiveDto) {
        return baseService.edit(gatewayMerchantReceiveDto, id);
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse delete(@PathVariable Optional<Long> id) {
        if (id.isPresent()) return baseService.delete(id.get());
        else return new ApiResponse<>(1, ID_EMPTY);
    }
}
