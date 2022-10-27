package uz.pdp.frontservice.controller;

import admin.receive.GatewayMerchantReceiveDto;
import admin.response.ApiResponse;
import admin.response.GatewayMerchantResponse;
import admin.response.GatewayResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

import static uz.pdp.frontservice.service.Service.getPage;
import static uz.pdp.frontservice.service.Service.isEmpty;

@Controller
@RequestMapping("/admin/gateway/merchant")
public class GatewayMerchantController {

    private final RestTemplate restTemplate;

    public GatewayMerchantController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/list/{pageSize}")
    public String getGatewayMerchantList(Model model, @PathVariable Optional<Integer> pageSize, @ModelAttribute GatewayMerchantReceiveDto gatewayMerchant) {
        List<GatewayMerchantResponse> gatewayMerchantList = (List<GatewayMerchantResponse>) restTemplate.postForEntity(
                "http://REST-SERVICE/api/gateway/merchant/list/" + getPage(pageSize) + "", gatewayMerchant, ApiResponse.class).getBody().getT();
        List<GatewayResponse> gatewayList = (List<GatewayResponse>) restTemplate.getForEntity(
                "http://REST-SERVICE/api/gateway/list/all", ApiResponse.class).getBody().getT();
        model.addAttribute("gatewayMerchant", new GatewayMerchantReceiveDto());
        model.addAttribute("gatewayMerchantList", gatewayMerchantList);
        model.addAttribute("gatewayList", gatewayList);
        model.addAttribute("page", getPage(pageSize));
        model.addAttribute("isEmpty", isEmpty(gatewayMerchantList.size()));
        return "admin/service/gatewayMerchant/list";
    }

    @GetMapping("/get/{id}")
    public String getGatewayMerchant(Model model, @PathVariable long id) {
        GatewayMerchantResponse gatewayMerchant = (GatewayMerchantResponse) restTemplate.getForEntity(
                "http://REST-SERVICE/api/gateway/merchant/get/" + id + "", ApiResponse.class).getBody().getT();
        List<GatewayResponse> gatewayList = (List<GatewayResponse>) restTemplate.getForEntity(
                "http://REST-SERVICE/api/gateway/list/all", ApiResponse.class).getBody().getT();
        model.addAttribute("gatewayMerchant", gatewayMerchant);
        model.addAttribute("gatewayList", gatewayList);
        return "admin/service/gatewayMerchant/edit";
    }

    @PostMapping("/add")
    public String addGatewayMerchant(@ModelAttribute GatewayMerchantReceiveDto gateway) {
        restTemplate.postForEntity("http://REST-SERVICE/api/gateway/merchant/add", gateway, ApiResponse.class);
        return "redirect:/admin/gateway/merchant/list/1";
    }

    @PostMapping("/edit/{id}")
    public String editGatewayMerchant(@PathVariable("id") long id, @ModelAttribute GatewayMerchantReceiveDto gateway) {
        restTemplate.postForEntity("http://REST-SERVICE/api/gateway/merchant/edit/" + id + "", gateway, ApiResponse.class);
        return "redirect:/admin/gateway/merchant/list/1";
    }

    @GetMapping("/delete/{id}")
    public String deleteGatewayMerchant(@PathVariable("id") long id) {
        restTemplate.delete("http://REST-SERVICE/api/gateway/merchant/delete/{id}", id);
        return "redirect:/admin/gateway/merchant/list/1";
    }
}
