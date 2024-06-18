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
public class WmsInventory implements Serializable {


    private String id;

    @TableField("item_no")
    private String itemNo;

    private Integer inventory;

    private Integer low;

    private Integer high;


}
