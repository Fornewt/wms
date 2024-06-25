package org.example.back.mapper;

import org.apache.ibatis.annotations.Delete;
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
    @Delete("DELETE FROM wms_Outbound_detail WHERE outbound_id = #{obId}")
    void deleteByobId(String obId);
}
