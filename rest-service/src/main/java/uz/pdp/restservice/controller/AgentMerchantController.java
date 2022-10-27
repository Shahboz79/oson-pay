package uz.pdp.restservice.controller;

import admin.receive.AgentMerchantReceiveDto;
import admin.response.ApiResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import uz.pdp.restservice.service.base.BaseService;

import java.util.Optional;

import static uz.pdp.restservice.service.base.ResponseMessage.ID_EMPTY;
import static uz.pdp.restservice.service.base.ResponseMessage.SUCCESS;

@RestController
@RequestMapping("/api/agent/merchant")
public class AgentMerchantController {

    private final BaseService baseService;

    public AgentMerchantController(@Qualifier("agentMerchantService") BaseService baseService) {
        this.baseService = baseService;
    }

    @PostMapping("/add")
    public ApiResponse add(@RequestBody AgentMerchantReceiveDto agentMerchantReceiveDto) {
        return baseService.add(agentMerchantReceiveDto);
    }

    @PostMapping("/list/{page}")
    public ApiResponse getList(@PathVariable int page, @RequestBody AgentMerchantReceiveDto agentMerchantReceiveDto) {
//        if (baseService.isNotNull(agentMerchantReceiveDto)) {
//            return baseService.getList(page, agentMerchantReceiveDto);
//        }
        return baseService.getList(page, agentMerchantReceiveDto);
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
    public ApiResponse edit(@PathVariable long id, @RequestBody AgentMerchantReceiveDto agentMerchantReceiveDto) {
        return baseService.edit(agentMerchantReceiveDto, id);
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse delete(@PathVariable Optional<Long> id) {
        if (id.isPresent()) return baseService.delete(id.get());
        else return new ApiResponse<>(1, ID_EMPTY);
    }
}
