package org.example.back.service.impl;

import org.example.back.domain.WmsInbound;
import org.example.back.domain.WmsInboundDetail;
import org.example.back.mapper.WmsInboundDetailMapper;
import org.example.back.mapper.WmsInboundMapper;
import org.example.back.service.WmsInboundService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Aloar
 * @since 2024-06-16
 */
@Service
public class WmsInboundServiceImpl extends ServiceImpl<WmsInboundMapper, WmsInbound> implements WmsInboundService {
    @Autowired
    WmsInboundMapper wmsInboundMapper;
    @Autowired
    WmsInboundDetailMapper wmsInboundDetailMapper;

    @Override
    public void deleteInboundById(String id){
        wmsInboundMapper.deleteById(id);
        // 删除该订单的所有物料
        wmsInboundDetailMapper.deleteByibId(id);


    }
}
