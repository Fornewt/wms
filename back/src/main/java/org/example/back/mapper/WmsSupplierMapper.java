package org.example.back.mapper;

import org.apache.ibatis.annotations.Select;
import org.example.back.domain.WmsSupplier;
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
public interface WmsSupplierMapper extends BaseMapper<WmsSupplier> {
    @Select("SELECT supplierName FROM wms_supplier")
    List<String> selectSupplierName();
}
