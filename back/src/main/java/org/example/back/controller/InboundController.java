package org.example.back.controller;

import com.sun.org.apache.bcel.internal.generic.RETURN;
import org.example.back.domain.InboundReq;
import org.example.back.service.impl.InboundServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class InboundController {
    @Autowired
    private InboundServiceImpl inboundService;

    @PostMapping("/inbound")
    public Map<String, Object> storeInboundOrder(@RequestBody InboundReq inboundReq) {
        System.out.println("Inserting detail: " + inboundReq);
        return inboundService.storeInboundOrder(inboundReq);

    }
}