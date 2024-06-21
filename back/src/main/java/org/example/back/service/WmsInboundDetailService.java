package org.example.back.service;

import org.example.back.domain.WmsInboundDetail;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Aloar
 * @since 2024-06-16
 */
public interface WmsInboundDetailService extends IService<WmsInboundDetail> {
    public List<WmsInboundDetail> inboundItemsInfo(String inboundId);
    public Map<String, Object> addRealQuantity(String ibdId,int quantity);
    public void deleteDetailById(String ibdId);
}
