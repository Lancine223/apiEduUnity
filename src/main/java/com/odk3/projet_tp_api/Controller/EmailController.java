package com.odk3.projet_tp_api.Controller;

import com.odk3.projet_tp_api.Service.EmailServiceImpl;
import com.odk3.projet_tp_api.model.EmailDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
//@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/sendEmail")
public class EmailController {
    @Autowired
    private EmailServiceImpl emailService;

    @PostMapping("send")
    public String sendEmail(@RequestBody EmailDetails details){
        return emailService.sendSimpleMail(details);
    }

}
