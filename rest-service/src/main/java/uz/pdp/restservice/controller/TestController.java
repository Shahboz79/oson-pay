package uz.pdp.restservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.restservice.service.users.UserService;

@RestController
@RequestMapping("/api/test")
public class TestController {

    private final UserService userService;

    public TestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/addSuperAdmin")
    public String getMessage(){
        userService.addSuperAdmin();
        return "success";
    }

}
