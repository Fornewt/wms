package org.example.back.service;

import org.example.back.domain.WmsItem;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Aloar
 * @since 2024-06-16
 */
public interface WmsItemService extends IService<WmsItem> {
    List<WmsItem> getAllItems();
    WmsItem getItemById(Long id);
    void addItem(WmsItem item);
    void updateItem(WmsItem item);
    void deleteItem(Long id);
    void saveOrUpdateItem(WmsItem item); // 添加 saveOrUpdateItem 方法

}
