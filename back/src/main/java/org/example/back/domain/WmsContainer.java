package org.example.back.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
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
public class WmsContainer implements Serializable {


    private String id;

    @TableField("item_no")
    private String itemNo;

    private String supplier;

    private String containerCode;

    private String container;


}
