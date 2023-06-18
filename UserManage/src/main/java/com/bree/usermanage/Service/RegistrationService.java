package com.bree.usermanage.Service;

import com.bree.usermanage.Model.RegistrationRequest;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {
    public String register(RegistrationRequest request) {
        return "Works";
    }
}
