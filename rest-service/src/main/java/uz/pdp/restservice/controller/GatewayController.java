package uz.pdp.restservice.controller;

import admin.receive.GatewayReceiveDto;
import admin.response.ApiResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import uz.pdp.restservice.service.base.BaseService;

import java.util.Optional;

import static uz.pdp.restservice.service.base.ResponseMessage.ID_EMPTY;
import static uz.pdp.restservice.service.base.ResponseMessage.SUCCESS;

@RestController
@RequestMapping("/api/gateway")
public class GatewayController {

    private final BaseService baseService;

    public GatewayController(@Qualifier("gatewayService") BaseService baseService) {
        this.baseService = baseService;
    }

    @PostMapping("/add")
    public ApiResponse add(@RequestBody GatewayReceiveDto gatewayReceiveDto) {
        return baseService.add(gatewayReceiveDto);
    }

    @PostMapping("/list/{page}")
    public ApiResponse getList(
            @PathVariable int page,
            @RequestBody GatewayReceiveDto gatewayReceiveDto) {
//        if (baseService.isNotNull(gatewayReceiveDto)) {
//            baseService.getList(page, gatewayReceiveDto);
//        }
        return baseService.getList(page, gatewayReceiveDto);
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
    public ApiResponse edit(@PathVariable long id, @RequestBody GatewayReceiveDto gatewayReceiveDto) {
        return baseService.edit(gatewayReceiveDto, id);
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse delete(@PathVariable Optional<Long> id) {
        if (id.isPresent()) return baseService.delete(id.get());
        else return new ApiResponse<>(1, ID_EMPTY);
    }
}
