package org.example.back.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.back.domain.WmsSupplier;
import org.example.back.mapper.WmsSupplierMapper;
import org.example.back.service.WmsSupplierService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *gi
 * @author Aloar
 * @since 2024-06-16
 */
@Service
public class WmsSupplierServiceImpl extends ServiceImpl<WmsSupplierMapper, WmsSupplier> implements WmsSupplierService {
    @Autowired
    private WmsSupplierMapper wmsSupplierMapper;

    public List<String> getSupplierName() {
        return wmsSupplierMapper.selectSupplierName();

    }
        public List<String> getAllSupplierNames () {
            QueryWrapper<WmsSupplier> queryWrapper = new QueryWrapper<>();
            List<WmsSupplier> suppliers = wmsSupplierMapper.selectList(queryWrapper);
            return suppliers.stream().map(WmsSupplier::getName).collect(Collectors.toList());
        }

        @Override
        public String getSupplierIdByName (String supplierName){
            QueryWrapper<WmsSupplier> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("supplierName", supplierName);
            WmsSupplier supplier = getOne(queryWrapper);
            return supplier != null ? supplier.getId() : null;
        }
    }

