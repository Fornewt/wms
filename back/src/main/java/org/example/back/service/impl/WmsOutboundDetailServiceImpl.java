package org.example.back.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.back.domain.WmsInboundDetail;
import org.example.back.domain.WmsInventory;
import org.example.back.domain.WmsOutboundDetail;
import org.example.back.mapper.WmsInboundDetailMapper;
import org.example.back.mapper.WmsInventoryMapper;
import org.example.back.mapper.WmsOutboundDetailMapper;
import org.example.back.mapper.WmsOutboundMapper;
import org.example.back.service.WmsOutboundDetailService;
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
public class WmsOutboundDetailServiceImpl extends ServiceImpl<WmsOutboundDetailMapper, WmsOutboundDetail> implements WmsOutboundDetailService {
    @Autowired
    private WmsOutboundDetailMapper wmsOutboundDetailMapper;
//    IService<WmsInventory> inventoryService;
    @Autowired
    private WmsInventoryMapper inventoryMapper;
    @Autowired
    private WmsInventoryMapper wmsInventoryMapper;
    @Autowired
    private WmsOutboundMapper wmsOutboundMapper;



    public Map<String, Object> addRealQuantity(String obdId, int quantity){
        // 分隔线，分隔不同零件
        System.out.println("--------分隔线---------");
        Map<String,Object> data = new HashMap<>(); // 用于打印查询结果信息
        // 出库实际变化量
        Integer originalRealQuantity = wmsOutboundDetailMapper.getRealQuantityByobdId(obdId);
        System.out.println("修改前的实际数量"+originalRealQuantity);
        Integer deltaInventory = -(quantity - originalRealQuantity);
        System.out.println("对库存的影响(变化量)："+deltaInventory);
        String itemNo = wmsOutboundDetailMapper.getItemNoByobdId(obdId);
        System.out.println("零件编号为:"+itemNo);
        Integer inventory = inventoryMapper.getInventoryByItemNo(itemNo);
        System.out.println("库存剩余量:"+inventory);

        // 如果出库的变化量大于库存量或者根本就不存在这种零件编号，应该阻止！ 并且直接返回
        if(inventory == null || -deltaInventory > inventory){
            data.put("此零件库存不足",obdId);
            data.put("库存剩余量",inventory);
            return data;
        }

        // 修改库存中的inventory
        wmsInventoryMapper.updateInventoryByItemNo(deltaInventory,itemNo);
        data.put("此零件出库成功",obdId);


        // 接下来更新明细
        UpdateWrapper<WmsOutboundDetail> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", obdId);
        WmsOutboundDetail updateDetail = new WmsOutboundDetail();
        updateDetail.setRealQuantity(quantity);
        // 执行更新操作
        wmsOutboundDetailMapper.update(updateDetail, updateWrapper);// 仅会更新在实体对象中显式设置的字段。其他字段保持不变
        // 查询id为obdId的明细的实际数量 返回到前端
//        QueryWrapper<WmsOutboundDetail> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("id", obdId);
//        WmsOutboundDetail Detail = wmsOutboundDetailMapper.selectOne(queryWrapper);
//        Integer updateQuantity = Detail.getRealQuantity();
//        data.put("成功将id为"+obdId+"的物料明细的实际数量修改为",updateQuantity);


        // 每一次出库修改，做一次状态变化
        // 先根据obdId获取outbound_id
        String obId = wmsOutboundDetailMapper.getOutboundIdByobdId(obdId);
        System.out.println("属于出库单:"+obdId);
        // 获取该obId下的所有明细，并且判断实际数量和计划数量的大小关系
        List<WmsOutboundDetail> details = wmsOutboundMapper.getDetailsByobId(obId);
        // 出库状态 0未出库 1部分出库 2全部出库
        boolean isPartlyOutbound = false;// 如果有明细存在实际数量大于0，则置为true
        boolean isTotallyOutbound = true;// 如果存在明细的实际数量不等于计划数量，则置为false

        for (WmsOutboundDetail detail : details) {
            int RQ = detail.getRealQuantity();
            int PQ = detail.getPlanQuantity();
            if(RQ > 0) isPartlyOutbound = true;
            if(PQ != RQ) isTotallyOutbound = false;
        }

        System.out.println("是否部分出库"+isPartlyOutbound+"是否完全出库"+isTotallyOutbound+"\n");

        if(!isPartlyOutbound && !isTotallyOutbound){
            wmsOutboundMapper.updateStatus(obId,0);
        }else if(isPartlyOutbound && !isTotallyOutbound){
            wmsOutboundMapper.updateStatus(obId,1);
        }else if(isTotallyOutbound && isPartlyOutbound){
            wmsOutboundMapper.updateStatus(obId,2);
        }

        return data;
    }
    public void deleteDetailById(String obdId){
        wmsOutboundDetailMapper.deleteById(obdId);
    }

    public List<WmsOutboundDetail> outboundItemsInfo(String outboundId){

        QueryWrapper<WmsOutboundDetail> queryWrapper = new QueryWrapper<>();// 用于查询
        queryWrapper.eq("outbound_id",outboundId); // 构建查询条件 WHERE user_name = userName
        List<WmsOutboundDetail> Details = (List<WmsOutboundDetail>) wmsOutboundDetailMapper.selectList(queryWrapper);
        System.out.println(Details);
        return Details;
    }
}
