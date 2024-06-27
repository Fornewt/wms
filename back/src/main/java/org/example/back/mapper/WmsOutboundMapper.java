package org.example.back.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.example.back.domain.WmsOutbound;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.back.domain.WmsOutboundDetail;

import java.util.List;

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

    // 根据obId获取所有明细
    @Select("SELECT * FROM wms_outbound_detail WHERE outbound_id = #{obId}")
    List<WmsOutboundDetail> getDetailsByobId(String obId);

    // 根据obId,changedStatus修改对应的status
    @Update("UPDATE wms_outbound SET outbound_status = #{changedStatus} WHERE id = #{obId}")
    void updateStatus(@Param("obId") String obId , @Param("changedStatus") Integer changedStatus);

}
