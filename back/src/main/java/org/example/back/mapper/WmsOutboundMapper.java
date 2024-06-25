package org.example.back.mapper;

import org.apache.ibatis.annotations.Select;
import org.example.back.domain.WmsOutbound;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Leizhenzi
 * @since 2024-06-25
 */
public interface WmsOutboundMapper extends BaseMapper<WmsOutbound> {
    @Select("SELECT COUNT(*) FROM wms_outbound where id = #{ibId}")
    Integer countByIbId(String ibId);
}
