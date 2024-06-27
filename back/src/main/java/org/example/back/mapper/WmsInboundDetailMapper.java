package org.example.back.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.example.back.domain.WmsInboundDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Aloar
 * @since 2024-06-16
 */
public interface WmsInboundDetailMapper extends BaseMapper<WmsInboundDetail> {
@Delete("DELETE FROM wms_inbound_detail WHERE inbound_id = #{ibId}")
    void deleteByibId(String ibId);

// 根据明细id获取实际数量
@Select("SELECT real_quantity FROM wms_inbound_detail WHERE id = #{ibdId}")
    Integer getRealQuantityByibdId(String ibdId);

// 根据明细获取item_no
    @Select("SELECT item_no FROM wms_inbound_detail WHERE id = #{ibdId}")
    String getItemNoByibdId(String ibId);// 查询不到默认返回为null

// 根据ibdId获取ibId
    @Select("SELECT inbound_id FROM wms_inbound_detail WHERE id=#{ibdId}")
    String getInboundIdByibdId(String ibdId);


}



