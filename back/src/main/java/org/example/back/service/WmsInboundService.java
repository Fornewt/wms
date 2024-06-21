package org.example.back.service;

import org.example.back.domain.WmsInbound;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Aloar
 * @since 2024-06-16
 */
public interface WmsInboundService extends IService<WmsInbound> {
    public void deleteInboundById(String id);
}
