package org.example.back.service;

import org.example.back.domain.WmsOutbound;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Leizhenzi
 * @since 2024-06-25
 */
public interface WmsOutboundService extends IService<WmsOutbound> {
    public void deleteOutboundById(String id);
}
