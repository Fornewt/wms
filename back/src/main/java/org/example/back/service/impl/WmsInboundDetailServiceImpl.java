package org.example.back.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.example.back.domain.SysUser;
import org.example.back.domain.WmsInboundDetail;
import org.example.back.mapper.WmsInboundDetailMapper;
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

    public List<WmsInboundDetail> inboundItemsInfo(String inboundId){

        QueryWrapper<WmsInboundDetail> queryWrapper = new QueryWrapper<>();// 用于查询
        queryWrapper.eq("inbound_id",inboundId); // 构建查询条件 WHERE user_name = userName
        List<WmsInboundDetail> Details = (List<WmsInboundDetail>) wmsInboundDetailMapper.selectList(queryWrapper);
        System.out.println(Details);
        return Details;
    }

    public Map<String, Object> addRealQuantity(String ibdId,int quantity){
        UpdateWrapper<WmsInboundDetail> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", ibdId);
        WmsInboundDetail updateDetail = new WmsInboundDetail();
        updateDetail.setRealQuantity(quantity);
        // 执行更新操作
        wmsInboundDetailMapper.update(updateDetail, updateWrapper);// 仅会更新在实体对象中显式设置的字段。其他字段保持不变
        // 再查询一下id为ibdId的明细的实际数量 返回到前端
        QueryWrapper<WmsInboundDetail> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", ibdId);
        WmsInboundDetail Detail = wmsInboundDetailMapper.selectOne(queryWrapper);
        Integer updateQuantity = Detail.getRealQuantity();


        Map<String,Object> data = new HashMap<>(); // 用于打印查询结果信息
        data.put("成功将id为"+ibdId+"的物料明细的实际数量修改为",updateQuantity);
        return data;
    }

    public void deleteDetailById(String ibdId){
        wmsInboundDetailMapper.deleteById(ibdId);
    }


}
