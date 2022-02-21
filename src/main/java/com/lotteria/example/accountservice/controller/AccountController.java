package com.lotteria.example.accountservice.controller;

import com.lotteria.example.accountservice.config.MQConfig;
import com.lotteria.example.accountservice.model.CustomMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.UUID;

@Controller
@RequestMapping("/api/account")
public class AccountController {

    @Autowired
    private RabbitTemplate template;

    @GetMapping()
    public ResponseEntity<?> getAccount() {
        return new ResponseEntity<>("account", HttpStatus.OK);
    }

    @PostMapping("/testNumber")
    public ResponseEntity<?> testNumber(@RequestBody CustomMessage message) {
        template.convertAndSend(MQConfig.EXCHANGE,
                MQConfig.ROUTING_KEY, message);
        return new ResponseEntity<>("in progress", HttpStatus.OK);
    }

}
