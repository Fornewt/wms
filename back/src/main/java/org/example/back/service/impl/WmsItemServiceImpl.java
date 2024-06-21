package org.example.back.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.back.domain.WmsItem;
import org.example.back.mapper.WmsItemMapper;
import org.example.back.service.WmsItemService;
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
public class WmsItemServiceImpl extends ServiceImpl<WmsItemMapper, WmsItem> implements WmsItemService {
    @Autowired
    private WmsItemMapper itemMapper;

    @Override
    public List<WmsItem> getAllItems() {
        return itemMapper.selectList(null);
    }

    @Override
    public WmsItem getItemById(Long id) {
        return itemMapper.selectById(id);
    }

    @Override
    public void addItem(WmsItem item) {
        itemMapper.insert(item);
    }

    @Override
    public void updateItem(WmsItem item) {
        itemMapper.updateById(item);
    }

    @Override
    public void deleteItem(Long id) {
        itemMapper.deleteById(id);
    }
    @Override
    public void saveOrUpdateItem(WmsItem item) {
        if (item.getId() == null || itemMapper.selectById(item.getId()) == null) {
            itemMapper.insert(item);
        } else {
            itemMapper.updateById(item);
        }
    }
    @Override
    public List<WmsItem> getItemsBySupplier(String supplier){
        QueryWrapper<WmsItem> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("supplier", supplier); // 设置查询条件为 supplier = 'su001'
        return itemMapper.selectList(queryWrapper); // 执行查询并返回结果
    }
    public List<WmsItem> getItemsBySupplierId(String supplierId) {
        QueryWrapper<WmsItem> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("supplier", supplierId);
        return list(queryWrapper);
    }

}
