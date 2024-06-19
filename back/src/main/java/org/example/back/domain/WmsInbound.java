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
public class WmsInbound implements Serializable {


    private String id;

    @TableField("inbound_no")
    private String inboundNo;

    @TableField("inbound_status")
    private Integer inboundStatus ;

    @TableField("inbound_time")
    private String inboundTime;

    private String supplier;


}
