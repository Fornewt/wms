package org.example.back.service.impl;

import org.example.back.domain.WmsContainer;
import org.example.back.mapper.WmsContainerMapper;
import org.example.back.service.WmsContainerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Aloar
 * @since 2024-06-16
 */
@Service
public class WmsContainerServiceImpl extends ServiceImpl<WmsContainerMapper, WmsContainer> implements WmsContainerService {

    @Autowired
    private  WmsContainerMapper wmsContainerMapper;

    @Override
    public List<Integer> getDistinctContainerCount(String itemNo, String supplier) {
        return wmsContainerMapper.countDistinctContainers(itemNo, supplier);
    }
}
