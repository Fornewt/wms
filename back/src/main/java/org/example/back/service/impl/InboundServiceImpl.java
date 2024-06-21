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
        // 获取入库单id，并且查询是否表中已经有重复
        String ibId = wmsInbound.getId();
        // 获取表中相同id的记录数
        Integer count = wmsInboundMapper.countByIbId(ibId);
        // 返回用
        Map<String, Object> result = new HashMap<>();
        if(count == 0){
        List<WmsInboundDetail> details = request.getWmsInboundDetailList();
        // 刚入库，状态肯定为“未入库”，即0
        wmsInbound.setInboundStatus(0);
        // 保存入库单
        wmsInboundMapper.insert(wmsInbound);

        // 保存入库单明细
        for(WmsInboundDetail detail : details) {
            detail.setInboundId(wmsInbound.getId());
            detail.setInboundNo(wmsInbound.getId());
            detail.setRealQuantity(0);// 实际数量初始化为0
            wmsInboundDetailMapper.insert(detail);

        }
        result.put("入库单保存成功", true);

        }else{
            result.put("入库单id重复","请重试!");
        }
        return result;
    }

    //返回所有的入库单
    public List<WmsInbound> getWmsInbound() {
        return wmsInboundMapper.selectList(null);
    }


}
