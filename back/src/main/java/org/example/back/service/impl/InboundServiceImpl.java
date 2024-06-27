package org.example.back.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.back.domain.InboundReq;
import org.example.back.domain.WmsInbound;
import org.example.back.domain.WmsInboundDetail;
import org.example.back.domain.WmsInventory;
import org.example.back.mapper.WmsInboundDetailMapper;
import org.example.back.mapper.WmsInboundMapper;
import org.example.back.mapper.WmsInventoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service // @Service 是 Spring 框架中的一个注解，用于标记服务层的组件
public class InboundServiceImpl {
    @Autowired
    private WmsInboundMapper wmsInboundMapper;
    @Autowired
    private WmsInboundDetailMapper wmsInboundDetailMapper;
    @Autowired
    private WmsInventoryMapper wmsInventoryMapper;



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

            // 增加库存量  库存的每一条由零件编号item_no决定  库存量为inventory
            // 遍历inventory  如果编号不存在创建一条新的记录
            QueryWrapper<WmsInventory> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("item_no",detail.getItemNo());
            // 查询是否存在该 item_no 的记录
            WmsInventory existingInventory = wmsInventoryMapper.selectOne(queryWrapper);

            if (existingInventory == null) {
                // 如果不存在，插入新的记录
                WmsInventory newInventory = new WmsInventory();
                newInventory.setItemNo(detail.getItemNo());
                newInventory.setInventory(0);// 刚刚入库，库存量默认为0
                // 设置其他字段，如果有的话
                // 使用当前日期和时间生成ID
                String idPrefix = "S-"; // 假设前缀是"S-"
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HHmm");
                String formattedDate = dateFormat.format(new Date());
                String newId = idPrefix + formattedDate;
                newInventory.setId(newId); // 设置ID

                // 插入新记录
                wmsInventoryMapper.insert(newInventory);
                System.out.println("插入新的零件编号成功：" + newInventory.getItemNo());
            } else {
                System.out.println("已存在的零件编号：" + existingInventory.getItemNo());
            }

        }

        result.put("成功", "创建入库单");

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
