package org.example.back.service.impl;

import org.example.back.domain.InboundReq;
import org.example.back.domain.WmsInbound;
import org.example.back.domain.WmsInboundDetail;
import org.example.back.mapper.WmsInboundDetailMapper;
import org.example.back.mapper.WmsInboundMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service // @Service 是 Spring 框架中的一个注解，用于标记服务层的组件
public class InboundServiceImpl {
    @Autowired
    private WmsInboundMapper wmsInboundMapper;
    @Autowired
    private WmsInboundDetailMapper wmsInboundDetailMapper;

    public Map<String, Object> storeInboundOrder(InboundReq request){
        WmsInbound wmsInbound = request.getWmsInbound();
        List<WmsInboundDetail> details = request.getWmsInboundDetailList();

        // 保存入库单
        wmsInboundMapper.insert(wmsInbound);
        // 测试
        System.out.println("Inserting detail: " + wmsInbound);

        // 保存入库单明细
        for(WmsInboundDetail detail : details){
//            detail.setInboundId(wmsInbound.getId()); 即保证每一条detail的inboudId都是对应的Inboud的id，这个可以在前端保证
            wmsInboundDetailMapper.insert(detail);

        }

        // 返回
        Map<String, Object> result = new HashMap<>();
        result.put("入库单保存成功", true);
        return result;

    }

}
