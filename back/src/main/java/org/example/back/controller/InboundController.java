package org.example.back.controller;

import com.sun.org.apache.bcel.internal.generic.RETURN;
import org.example.back.domain.InboundReq;
import org.example.back.domain.WmsInbound;
import org.example.back.service.impl.InboundServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class InboundController {
    @Autowired
    private InboundServiceImpl inboundService;

    @PostMapping("/inbound")
    public Map<String, Object> storeInboundOrder(@RequestBody InboundReq inboundReq) {
        System.out.println("新入库订单的详细信息：" + inboundReq);
        return inboundService.storeInboundOrder(inboundReq);

    }

    @GetMapping("/getInbound")
    public List getInbound(){
        List<WmsInbound> inboundList = inboundService.getWmsInbound();
        return inboundList;
    }
}
