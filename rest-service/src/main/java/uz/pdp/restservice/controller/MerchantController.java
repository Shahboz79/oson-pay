package uz.pdp.restservice.controller;

import admin.receive.MerchantReceiveDto;
import admin.response.ApiResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import uz.pdp.restservice.service.base.BaseService;

import java.util.Optional;

import static uz.pdp.restservice.service.base.ResponseMessage.ID_EMPTY;
import static uz.pdp.restservice.service.base.ResponseMessage.SUCCESS;

@RestController
@RequestMapping("/api/merchant")
public class MerchantController {

    private final BaseService baseService;

    public MerchantController(@Qualifier("merchantService") BaseService baseService) {
        this.baseService = baseService;
    }

    @PostMapping("/add")
    public ApiResponse add(@RequestBody MerchantReceiveDto merchantReceiveDto) {
        return baseService.add(merchantReceiveDto);
    }

    @PostMapping("/list/{page}")
    public ApiResponse getList(@PathVariable int page, @RequestBody MerchantReceiveDto merchantReceiveDto) {
        if (baseService.isNotNull(merchantReceiveDto)) {
            return baseService.getList(page, merchantReceiveDto);
        }
        return baseService.getList(page);
    }

    @GetMapping("/list/all")
    public ApiResponse getAllList() {
        return baseService.getAllList();
    }

    @GetMapping("/get/{id}")
    public ApiResponse get(@PathVariable Optional<Long> id) {
        return new ApiResponse<>(0, SUCCESS, baseService.getById(id.get()));
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse delete(@PathVariable Optional<Long> id) {
        if (id.isPresent()) return baseService.delete(id.get());
        else return new ApiResponse<>(1, ID_EMPTY);
    }
}
