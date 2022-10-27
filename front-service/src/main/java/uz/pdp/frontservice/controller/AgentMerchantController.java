package uz.pdp.frontservice.controller;

import admin.receive.AgentMerchantReceiveDto;
import admin.response.AgentMerchantResponse;
import admin.response.AgentResponse;
import admin.response.ApiResponse;
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
@RequestMapping("/admin/agent/merchant")
public class AgentMerchantController {
    private final RestTemplate restTemplate;

    public AgentMerchantController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/list/{pageSize}")
    public String getAgentMerchantList(
            Model model,
            @PathVariable Optional<Integer> pageSize,
            @ModelAttribute AgentMerchantReceiveDto searchAgentMerchant
    ) {
        List<AgentMerchantResponse> agentMerchantList = (List<AgentMerchantResponse>) restTemplate.postForEntity(
                "http://REST-SERVICE/api/agent/merchant/list/" + getPage(pageSize) + "", searchAgentMerchant, ApiResponse.class).getBody().getT();
        List<AgentResponse> agentList = (List<AgentResponse>) restTemplate.getForEntity(
                "http://REST-SERVICE/api/agent/list/all", ApiResponse.class).getBody().getT();
        List<MerchantResponse> merchantList = (List<MerchantResponse>) restTemplate.getForEntity(
                "http://REST-SERVICE/api/merchant/list/all", ApiResponse.class).getBody().getT();
        model.addAttribute("agentMerchant", new AgentMerchantReceiveDto());
        model.addAttribute("agentMerchantList", agentMerchantList);
        model.addAttribute("agentList", agentList);
        model.addAttribute("merchantList", merchantList);
        model.addAttribute("page", getPage(pageSize));
        model.addAttribute("isEmpty", isEmpty(agentMerchantList.size()));
        return "admin/service/agentMerchant/list";
    }

    @GetMapping("/get/{id}")
    public String getAgentMerchant(Model model, @PathVariable long id) {
        AgentMerchantResponse agentMerchant = (AgentMerchantResponse) restTemplate.getForEntity(
                "http://REST-SERVICE/api/agent/merchant/get/" + id + "", ApiResponse.class).getBody().getT();
        List<AgentResponse> agentList = (List<AgentResponse>) restTemplate.getForEntity(
                "http://REST-SERVICE/api/agent/list/all", ApiResponse.class).getBody().getT();
        List<MerchantResponse> merchantList = (List<MerchantResponse>) restTemplate.getForEntity(
                "http://REST-SERVICE/api/merchant/list/all", ApiResponse.class);
        model.addAttribute("agentMerchant", agentMerchant);
        model.addAttribute("agentList", agentList);
        model.addAttribute("merchantList", merchantList);
        return "admin/service/agentMerchant/edit";
    }

    @PostMapping("/add")
    public String addAgentMerchant(@ModelAttribute AgentMerchantReceiveDto agentMerchant) {
        restTemplate.postForEntity("http://REST-SERVICE/api/agent/merchant/add", agentMerchant, ApiResponse.class);
        return "redirect:/admin/agent/merchant/list/1";
    }

    @PostMapping("/edit/{id}")
    public String editAgent(@PathVariable("id") long id, @ModelAttribute AgentMerchantReceiveDto agent) {
        restTemplate.postForEntity("http://REST-SERVICE/api/agent/merchant/edit/" + id + "", agent, ApiResponse.class);
        return "redirect:/admin/agent/merchant/list/1";
    }

    @GetMapping("/delete/{id}")
    public String deleteAgent(@PathVariable("id") long id) {
        restTemplate.delete("http://REST-SERVICE/api/agent/merchant/delete/{id}", id);
        return "redirect:/admin/agent/merchant/list/1";
    }
}

