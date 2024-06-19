package org.example.back.service;

import org.example.back.domain.WmsContainer;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Aloar
 * @since 2024-06-16
 */
public interface WmsContainerService extends IService<WmsContainer> {
    List<Integer> getDistinctContainerCount(String itemNo, String supplier);

}
