package org.example.back.controller;


import org.example.back.domain.InboundReq;
import org.example.back.domain.OutboundReq;
import org.example.back.domain.WmsInbound;
import org.example.back.domain.WmsOutbound;
import org.example.back.service.impl.InboundServiceImpl;
import org.example.back.service.impl.WmsOutboundServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Leizhenzi
 * @since 2024-06-25
 */
@RestController
@RequestMapping("/wmsOutbound")
public class WmsOutboundController {
    @Autowired
    private WmsOutboundServiceImpl OutboundService;
    @GetMapping("/getOutbound")
    public List getOutbound(){
        List<WmsOutbound> outboundList = OutboundService.getWmsOutbound();
        System.out.println(outboundList);
        return outboundList;
    }
    @PostMapping("/outbound")
    public Map<String, Object> storeOutboundOrder(@RequestBody OutboundReq outboundReq) {
        System.out.println("Inserting detail: " + outboundReq);
        return OutboundService.storeOutboundOrder(outboundReq);

    }
    @DeleteMapping("/deleteOutboundById")
    public void deleteOutboundById(@RequestParam String id) {// 根据订单id删除订单 同时要删除所有的物料
        OutboundService.deleteOutboundById(id);
    }
}

