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

    public Map<String, Object> addRealQuantity(String obdId, int quantity){
        Map<String,Object> data = new HashMap<>(); // 用于打印查询结果信息
        // 出库实际变化量
        Integer originalRealQuantity = wmsOutboundDetailMapper.getRealQuantityByobdId(obdId);
        System.out.println("原来的实际数量"+originalRealQuantity);
        Integer deltaInventory = -(quantity - originalRealQuantity);
        System.out.println("变化量"+deltaInventory);

//        UpdateWrapper<WmsInventory> updateWrapper1 = new UpdateWrapper<>();
        String itemNo = wmsOutboundDetailMapper.getItemNoByobdId(obdId);
        System.out.println("零件编号为"+itemNo);

        Integer returnNull = inventoryMapper.getInventoryByItemNo(itemNo);
        System.out.println("库存中存在此编号吗？"+returnNull+"\n");
        // 如果出库的变化量大于库存量或者根本就不存在这种零件编号，应该阻止！ 并且直接返回
        if(inventoryMapper.getInventoryByItemNo(itemNo) == null || -deltaInventory > inventoryMapper.getInventoryByItemNo(itemNo)){
            data.put("库存不足",obdId);
            data.put("该编号零件剩余",inventoryMapper.getInventoryByItemNo(itemNo));
//            data.put("obdId为",obdId);
            return data;
        }
        // 修改库存中的inventory
        wmsInventoryMapper.updateInventoryByItemNo(deltaInventory,itemNo);
        data.put("成功出库",obdId);

//        updateWrapper1.eq("item_no", itemNo).set("inventory", "inventory + " + deltaInventory);;
//        inventoryService.update(updateWrapper1);

        // 接下来更新明细
        UpdateWrapper<WmsOutboundDetail> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", obdId);
        WmsOutboundDetail updateDetail = new WmsOutboundDetail();
        updateDetail.setRealQuantity(quantity);
        // 执行更新操作
        wmsOutboundDetailMapper.update(updateDetail, updateWrapper);// 仅会更新在实体对象中显式设置的字段。其他字段保持不变
        // 查询id为obdId的明细的实际数量 返回到前端
        QueryWrapper<WmsOutboundDetail> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", obdId);
        WmsOutboundDetail Detail = wmsOutboundDetailMapper.selectOne(queryWrapper);
        Integer updateQuantity = Detail.getRealQuantity();
        data.put("成功将id为"+obdId+"的物料明细的实际数量修改为",updateQuantity);
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
