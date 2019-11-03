package com.productionmanagement.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/base")
public class HelperController {

    @GetMapping("/newUuid")
    public String getUuid() {
        UUID uuid = UUID.randomUUID();

        return uuid.toString();
    }
}
