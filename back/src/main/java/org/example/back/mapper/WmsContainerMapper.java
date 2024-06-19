package org.example.back.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.example.back.domain.WmsContainer;
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
public interface WmsContainerMapper extends BaseMapper<WmsContainer> {
    @Select("SELECT  container FROM wms_container WHERE item_no = #{itemNo} AND supplier = #{supplier}")
    List<Integer> countDistinctContainers(@Param("itemNo") String itemNo, @Param("supplier") String supplier);
}
