package org.example.back.service.impl;

import org.example.back.domain.*;
import org.example.back.mapper.WmsInboundDetailMapper;
import org.example.back.mapper.WmsInboundMapper;
import org.example.back.mapper.WmsOutboundDetailMapper;
import org.example.back.mapper.WmsOutboundMapper;
import org.example.back.service.WmsOutboundService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Leizhenzi
 * @since 2024-06-25
 */
@Service
public class WmsOutboundServiceImpl extends ServiceImpl<WmsOutboundMapper, WmsOutbound> implements WmsOutboundService {
    @Autowired
    private WmsOutboundMapper wmsOutboundMapper;
    @Autowired
    private WmsOutboundDetailMapper wmsOutboundDetailMapper;

    //返回所有的出库单
    public List<WmsOutbound> getWmsOutbound() {
        return wmsOutboundMapper.selectList(null);
    }

    public Map<String, Object> storeOutboundOrder(OutboundReq request){
        WmsOutbound wmsOutbound = request.getWmsOutbound();
        // 获取出库单id，并且查询是否表中已经有重复
        String ibId = wmsOutbound.getId();
        // 获取表中相同id的记录数
        Integer count = wmsOutboundMapper.countByIbId(ibId);

        Map<String, Object> result = new HashMap<>();
        if(count == 0){
            List<WmsOutboundDetail> details = request.getWmsOutboundDetailList();
            // 未出库状态为1
            wmsOutbound.setOutboundStatus(1);
            // 保存出库单
            wmsOutboundMapper.insert(wmsOutbound);

            // 保存出库单明细
            for(WmsOutboundDetail detail : details) {
                detail.setOutboundId(wmsOutbound.getId());
                detail.setOutboundNo(wmsOutbound.getId());
                detail.setRealQuantity(0);// 实际数量初始化为0
                wmsOutboundDetailMapper.insert(detail);

            }
            result.put("出库单保存成功", true);

        }else{
            result.put("出库单id重复","请重试!");
        }
        return result;
    }

    @Override
    public void deleteOutboundById(String id){
        wmsOutboundMapper.deleteById(id);
        // 删除该订单的所有物料
        wmsOutboundDetailMapper.deleteByobId(id);
    }

}
