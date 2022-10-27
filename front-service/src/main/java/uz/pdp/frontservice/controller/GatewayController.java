package uz.pdp.frontservice.controller;

import admin.receive.GatewayReceiveDto;
import admin.response.ApiResponse;
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
@RequestMapping("/admin/gateway")
public class GatewayController {
    private final RestTemplate restTemplate;

    public GatewayController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/list/{pageSize}")
    public String getGatewayList(Model model, @PathVariable Optional<Integer> pageSize, @ModelAttribute GatewayReceiveDto gateway) {
        List<GatewayResponse> gatewayList = (List<GatewayResponse>) restTemplate.postForEntity(
                "http://REST-SERVICE/api/gateway/list/" + getPage(pageSize) + "", gateway, ApiResponse.class).getBody().getT();
        model.addAttribute("gateway", new GatewayReceiveDto());
        model.addAttribute("gatewayList", gatewayList);
        model.addAttribute("page", getPage(pageSize));
        model.addAttribute("isEmpty", isEmpty(gatewayList.size()));
        return "admin/service/gateway/list";
    }

    @GetMapping("/get/{id}")
    public String getGateway(Model model, @PathVariable long id) {
        GatewayResponse gateway = (GatewayResponse) restTemplate.getForEntity(
                "http://REST-SERVICE/api/gateway/get/" + id + "", ApiResponse.class).getBody().getT();
        model.addAttribute("gateway", gateway);
        return "admin/service/gateway/edit";
    }

    @PostMapping("/edit/{id}")
    public String editGateway(@PathVariable("id") long id, @ModelAttribute GatewayReceiveDto gateway) {
        restTemplate.postForEntity("http://REST-SERVICE/api/gateway/edit/" + id + "", gateway, ApiResponse.class);
        return "redirect:/admin/gateway/list/1";
    }

    @PostMapping("/add")
    public String addGateway(@ModelAttribute GatewayReceiveDto gateway) {
        restTemplate.postForEntity("http://REST-SERVICE/api/gateway/add", gateway, ApiResponse.class);
        return "redirect:/admin/gateway/list/1";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteGateway(@PathVariable("id") long id) {
        restTemplate.delete("http://REST-SERVICE/api/gateway/delete/{id}", id);
        return "redirect:/admin/gateway/list/1";
    }
}
