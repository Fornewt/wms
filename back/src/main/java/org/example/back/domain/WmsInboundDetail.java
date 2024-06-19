package org.example.back.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
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
public class WmsInboundDetail implements Serializable {

    private String id;

    @TableField("inbound_id")
    private String inboundId;

    @TableField("inbound_no")
    private String inboundNo;

    @TableField("item_no")
    private String itemNo;

    private Integer container;

    @TableField("plan_quantity")
    private Integer planQuantity;

    @TableField("real_quantity")
    private Integer realQuantity;


}
