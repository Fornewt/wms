package org.example.back.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
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

// 根据ibId,changedStatus修改对应的status
    @Update("UPDATE wms_inbound SET inbound_status = #{changedStatus} WHERE id = #{ibId}")
    void updateStatus(@Param("ibId") String ibId ,@Param("changedStatus") Integer changedStatus);
}
