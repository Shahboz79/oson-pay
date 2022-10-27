package uz.pdp.frontservice.controller;

import admin.receive.MerchantReceiveDto;
import admin.response.ApiResponse;
import admin.response.GatewayMerchantResponse;
import admin.response.MerchantResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

import static uz.pdp.frontservice.service.Service.getPage;
import static uz.pdp.frontservice.service.Service.isEmpty;

@Controller
@RequestMapping("/admin/merchant")
public class MerchantController {
    private final RestTemplate restTemplate;

    public MerchantController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/list/{pageSize}")
    public String getMerchantList(Model model, @PathVariable Optional<Integer> pageSize, @ModelAttribute MerchantReceiveDto merchant) {
        List<MerchantResponse> merchantList = (List<MerchantResponse>) restTemplate.postForEntity(
                "http://REST-SERVICE/api/merchant/list/" + getPage(pageSize) + "", merchant, ApiResponse.class).getBody().getT();
        List<GatewayMerchantResponse> gatewayMerchantList = (List<GatewayMerchantResponse>) restTemplate.getForEntity(
                "http://REST-SERVICE/api/gateway/merchant/list/all", ApiResponse.class).getBody().getT();
        model.addAttribute("merchant", new MerchantReceiveDto());
        model.addAttribute("merchantList", merchantList);
        model.addAttribute("gatewayMerchantList", gatewayMerchantList);
        model.addAttribute("page", getPage(pageSize));
        model.addAttribute("isEmpty", isEmpty(merchantList.size()));
        return "admin/service/merchant/list";
    }

    @PostMapping("/add")
    public String addMerchant(@ModelAttribute MerchantReceiveDto merchant) {
        restTemplate.postForEntity("http://REST-SERVICE/api/merchant/add", merchant, ApiResponse.class);
        return "redirect:/admin/merchant/list/1";
    }

    @GetMapping("/delete/{id}")
    public String deleteMerchant(@PathVariable("id") long id) {
        restTemplate.delete("http://REST-SERVICE/api/merchant/delete/{id}(id=" + id + ")");
        return "redirect:/admin/merchant/list/1";
    }
}
