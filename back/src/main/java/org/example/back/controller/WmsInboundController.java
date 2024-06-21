package org.example.back.controller;


import org.example.back.service.WmsInboundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Aloar
 * @since 2024-06-16
 */
@RestController
@RequestMapping("/wmsInbound")
public class WmsInboundController {
    @Autowired
    WmsInboundService wmsInboundService;
    @DeleteMapping("/deleteInboundById")
    public void deleteInboundById(@RequestParam String id) {// 根据订单id删除订单 同时要删除所有的物料
        wmsInboundService.deleteInboundById(id);
    }

}

