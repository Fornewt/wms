package org.example.back.service;

import org.example.back.domain.WmsSupplier;
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
public interface WmsSupplierService extends IService<WmsSupplier> {
    public List<String > getSupplierName();

}
