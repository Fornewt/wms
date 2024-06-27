package org.example.back.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.back.domain.SysUser;
import org.example.back.domain.WmsInboundDetail;
import org.example.back.domain.WmsInventory;
import org.example.back.mapper.WmsInboundDetailMapper;
import org.example.back.mapper.WmsInboundMapper;
import org.example.back.mapper.WmsInventoryMapper;
import org.example.back.service.WmsInboundDetailService;
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
 * @author Aloar
 * @since 2024-06-16
 */
@Service
public class WmsInboundDetailServiceImpl extends ServiceImpl<WmsInboundDetailMapper, WmsInboundDetail> implements WmsInboundDetailService {
    @Autowired
            private WmsInboundDetailMapper wmsInboundDetailMapper;

    @Autowired
            private WmsInventoryMapper wmsInventoryMapper;

    @Autowired
            private WmsInboundMapper wmsInboundMapper;

    IService<WmsInventory> inventoryService;
    public List<WmsInboundDetail> inboundItemsInfo(String inboundId){

        QueryWrapper<WmsInboundDetail> queryWrapper = new QueryWrapper<>();// 用于查询
        queryWrapper.eq("inbound_id",inboundId); // 构建查询条件 WHERE user_name = userName
        List<WmsInboundDetail> Details = (List<WmsInboundDetail>) wmsInboundDetailMapper.selectList(queryWrapper);
        return Details;
    }

    public Map<String, Object> addRealQuantity(String ibdId,int quantity){
        // 分隔线，分隔不同零件
        System.out.println("--------分隔线---------");

        // 先修改库存
        Integer originalRealQuantity = wmsInboundDetailMapper.getRealQuantityByibdId(ibdId);
        System.out.println("修改前的实际数量:"+originalRealQuantity);
        Integer deltaInventory = quantity - originalRealQuantity;
        System.out.println("对库存的影响(变化量)："+deltaInventory);
        String itemNo = wmsInboundDetailMapper.getItemNoByibdId(ibdId);
        System.out.println("零件编号为："+itemNo);
        // 修改库存中的inventory
        wmsInventoryMapper.updateInventoryByItemNo(deltaInventory,itemNo);


        // 修改明细
        UpdateWrapper<WmsInboundDetail> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", ibdId);
        WmsInboundDetail updateDetail = new WmsInboundDetail();
        updateDetail.setRealQuantity(quantity);
        // 执行更新操作
        wmsInboundDetailMapper.update(updateDetail, updateWrapper);// 仅会更新在实体对象中显式设置的字段。其他字段保持不变

        // 每一次入库修改，做一次状态判定和设置
        // 先根据ibdId获取inbound_id
        String ibId = wmsInboundDetailMapper.getInboundIdByibdId(ibdId);
        System.out.println("属于入库单:"+ibdId+"\n");
        // 获取该ibId下的所有明细，并且判断实际数量和计划数量的关系
        List<WmsInboundDetail> details = inboundItemsInfo(ibId);
        // 入库状态 0未入库 1部分入库 2全部入库
        boolean isPartlyInbound = false;// 如果有明细存在实际数量大于0，则置为true
        boolean isTotallyInbound = true;// 如果存在明细的实际数量不等于计划数量，则置为false

        for (WmsInboundDetail detail : details) {
            int RQ = detail.getRealQuantity();// 解包装Integer为int，不然不能用 ！=
            int PQ = detail.getPlanQuantity();
            if(RQ > 0)isPartlyInbound = true;
            if(PQ != RQ)isTotallyInbound = false;
        }

        if(!isPartlyInbound && !isTotallyInbound){
            wmsInboundMapper.updateStatus(ibId,0);
        }else if(isPartlyInbound && !isTotallyInbound){
            wmsInboundMapper.updateStatus(ibId,1);
        }else if(isTotallyInbound && isPartlyInbound){
            wmsInboundMapper.updateStatus(ibId,2);
        }

        Map<String,Object> data = new HashMap<>(); // 用于打印查询结果信息
        data.put("成功","修改明细，进入库存");
        return data;
    }

    public void deleteDetailById(String ibdId){
        wmsInboundDetailMapper.deleteById(ibdId);
    }


}
