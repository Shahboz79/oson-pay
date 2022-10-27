package uz.pdp.frontservice.controller;

import admin.receive.AgentReceiveDto;
import admin.response.AgentResponse;
import admin.response.ApiResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

import static uz.pdp.frontservice.service.Service.getPage;
import static uz.pdp.frontservice.service.Service.isEmpty;

@Controller
@RequestMapping("/admin/agent")
public class AgentController {
    private final RestTemplate restTemplate;

    public AgentController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/list/{pageSize}")
    public String getAgentList(Model model, @PathVariable Optional<Integer> pageSize, @ModelAttribute AgentReceiveDto searchAgent) {
        System.out.println("searchAgent.getName() = " + searchAgent.getName());
        List<AgentResponse> agentList = (List<AgentResponse>) restTemplate.postForEntity(
                "http://REST-SERVICE/api/agent/list/" + getPage(pageSize) + "", searchAgent, ApiResponse.class).getBody().getT();
        model.addAttribute("agent", new AgentReceiveDto());
        model.addAttribute("agentList", agentList);
        model.addAttribute("page", getPage(pageSize));
        model.addAttribute("isEmpty", isEmpty(agentList.size()));
        return "/admin/service/agent/list";
    }

    @GetMapping("/get/{id}")
    public String getAgent(Model model, @PathVariable long id) {
        AgentResponse agent = (AgentResponse) restTemplate.getForEntity(
                "http://REST-SERVICE/api/agent/get/" + id + "", ApiResponse.class).getBody().getT();
        model.addAttribute("agent", agent);
        return "admin/service/agent/edit";
    }

    @PostMapping("/add")
    public String addAgent(@ModelAttribute AgentReceiveDto agent) {
        restTemplate.postForEntity("http://REST-SERVICE/api/agent/add", agent, ApiResponse.class);
        return "redirect:/admin/agent/list/1";
    }

    @PostMapping("/edit/{id}")
    public String editAgent(@PathVariable("id") long id, @ModelAttribute AgentReceiveDto agent) {
        restTemplate.postForEntity("http://REST-SERVICE/api/agent/edit/" + id + "", agent, ApiResponse.class);
        return "redirect:/admin/agent/list/1";
    }

    @GetMapping("/delete/{id}")
    public String deleteAgent(@PathVariable("id") long id) {
        restTemplate.delete("http://REST-SERVICE/api/agent/delete/{id}", id);
        return "redirect:/admin/agent/list/1";
    }
}
