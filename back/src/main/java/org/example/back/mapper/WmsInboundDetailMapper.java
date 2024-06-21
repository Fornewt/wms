package org.example.back.mapper;

import org.apache.ibatis.annotations.Delete;
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
}
