package org.example.back.mapper;

import org.apache.ibatis.annotations.Select;
import org.example.back.domain.WmsInbound;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Aloar
 * @since 2024-06-16
 */
public interface WmsInboundMapper extends BaseMapper<WmsInbound> {
@Select("SELECT COUNT(*) FROM wms_inbound where id = #{ibId}")
Integer countByIbId(String ibId);


}
