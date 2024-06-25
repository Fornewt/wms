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
 * @author Leizhenzi
 * @since 2024-06-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WmsOutboundDetail implements Serializable {


    private String id;

    @TableField("outbound_id")
    private String outboundId;

    @TableField("outbound_no")
    private String outboundNo;

    @TableField("item_no")
    private String itemNo;

    private Integer container;

    @TableField("plan_quantity")
    private Integer planQuantity;

    @TableField("real_quantity")
    private Integer realQuantity;


}
