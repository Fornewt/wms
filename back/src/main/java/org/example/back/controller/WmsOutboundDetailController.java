package org.example.back.controller;


import org.example.back.domain.WmsInbound;
import org.example.back.domain.WmsInboundDetail;
import org.example.back.domain.WmsOutbound;
import org.example.back.domain.WmsOutboundDetail;
import org.example.back.service.WmsInboundDetailService;
import org.example.back.service.WmsOutboundDetailService;
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
@RequestMapping("/wmsOutboundDetail")
public class WmsOutboundDetailController {
    @Autowired
    private WmsOutboundDetailService wmsOutboundDetailService;

    @PostMapping("/addRealQuantity")
    public Map<String, Object> addRealQuantity(@RequestParam("obdId") String obdId, @RequestParam("quantity") int quantity){
        return wmsOutboundDetailService.addRealQuantity(obdId,quantity);

    }

    @DeleteMapping("/deleteDetailById")
    public void deleteDetailById(@RequestParam("obdId") String obdId){
        wmsOutboundDetailService.deleteDetailById(obdId);
    }

    @PostMapping("/outboundItemsInfo")
    public List<WmsOutboundDetail> OutboundItemsInfo(@RequestBody WmsOutbound wmsOutbound){
        String outboundId = wmsOutbound.getId();
        return wmsOutboundDetailService.outboundItemsInfo(outboundId);

    }
}

