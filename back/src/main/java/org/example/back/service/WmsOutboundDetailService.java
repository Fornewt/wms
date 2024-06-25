package org.example.back.service;

import org.example.back.domain.WmsInboundDetail;
import org.example.back.domain.WmsOutboundDetail;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Leizhenzi
 * @since 2024-06-25
 */
public interface WmsOutboundDetailService extends IService<WmsOutboundDetail> {
    public Map<String, Object> addRealQuantity(String obdId, int quantity);
    public void deleteDetailById(String obdId);
    public List<WmsOutboundDetail> outboundItemsInfo(String outboundId);
}
