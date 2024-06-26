package org.example.back.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.example.back.domain.WmsInventory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Aloar
 * @since 2024-06-16
 */
public interface WmsInventoryMapper extends BaseMapper<WmsInventory> {
@Select("SELECT inventory FROM wms_inventory WHERE item_no = #{itemNo}")
    Integer getInventoryByItemNo(String itemNo);

@Update("UPDATE wms_inventory SET inventory = inventory + #{deltaInventory} WHERE item_no = #{itemNo}")
    // 这里似乎因为有两个参数，所以必须写@Param
    void updateInventoryByItemNo(@Param("deltaInventory") Integer deltaInventory, @Param("itemNo") String itemNo);

}
