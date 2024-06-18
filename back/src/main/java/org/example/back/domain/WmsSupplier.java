package org.example.back.domain;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author Aloar
 * @since 2024-06-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WmsSupplier implements Serializable {


    private String id;

    @TableField("supplierCode")
    private String code;

    @TableField("supplierName")
    private String name;


}
