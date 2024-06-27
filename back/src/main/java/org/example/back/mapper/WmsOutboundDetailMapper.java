package org.example.back.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.example.back.domain.WmsOutboundDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Leizhenzi
 * @since 2024-06-25
 */
public interface WmsOutboundDetailMapper extends BaseMapper<WmsOutboundDetail> {
    @Delete("DELETE FROM wms_outbound_detail WHERE outbound_id = #{obId}")
    void deleteByobId(String obId);

    // 根据明细Id获取实际数量
    @Select("SELECT real_quantity FROM wms_outbound_detail WHERE id = #{obdId}")
    Integer getRealQuantityByobdId(String obdId);

    // 根据明细id获取item_no
    @Select("SELECT item_no FROM wms_outbound_detail WHERE id = #{obdId}")
    String getItemNoByobdId(String obdId);

    // 根据obdId获取obId
    @Select("SELECT outbound_id FROM wms_outbound_detail WHERE id = #{obdId}")
    String getOutboundIdByobdId(String obdId);



}

