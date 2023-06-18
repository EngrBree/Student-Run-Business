package com.bree.usermanage.Controller;

import com.bree.usermanage.Model.RegistrationRequest;
import com.bree.usermanage.Service.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users/register")
@AllArgsConstructor
public class RegistrationController {
    private final RegistrationService registrationService;

    @PostMapping
    public  String register(@RequestBody RegistrationRequest request){
        return registrationService.register(request);
    }
}
