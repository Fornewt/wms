package org.example.back.controller;


import org.apache.ibatis.annotations.Delete;
import org.example.back.domain.SysUser;
import org.example.back.domain.WmsInbound;
import org.example.back.domain.WmsInboundDetail;
import org.example.back.service.WmsInboundDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Aloar
 * @since 2024-06-16
 */
@RestController
@RequestMapping("/wmsInboundDetail")
public class WmsInboundDetailController {
    @Autowired
    private WmsInboundDetailService wmsInboundDetailService;

    @PostMapping("/inboundItemsInfo")
    public List<WmsInboundDetail> inboundItemsInfo(@RequestBody WmsInbound wmsInbound){
        String inboundId = wmsInbound.getId();
        System.out.println(inboundId);
        return wmsInboundDetailService.inboundItemsInfo(inboundId);

    }

    @PostMapping("/addRealQuantity")
    public Map<String, Object> addRealQuantity(@RequestParam("ibdId") String ibdId,@RequestParam("quantity") int quantity){
//        System.out.println(ibdId);
//        System.out.println(quantity);
        return wmsInboundDetailService.addRealQuantity(ibdId,quantity);

    }

    @DeleteMapping("/deleteDetailById")
    public void deleteDetailById(@RequestParam("ibdId") String ibdId){
        wmsInboundDetailService.deleteDetailById(ibdId);
    }


}

