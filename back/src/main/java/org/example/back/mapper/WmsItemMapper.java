package org.example.back.mapper;

import org.apache.ibatis.annotations.Select;
import org.example.back.domain.WmsItem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Aloar
 * @since 2024-06-16
 */
public interface WmsItemMapper extends BaseMapper<WmsItem> {
@Select("SELECT DISTINCT item_no,item_name FROM wms_item")
List<WmsItem> getItemList();
}
