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
public class WmsOutbound implements Serializable {


    private String id;

    @TableField("outbound_no")
    private String outboundNo;

    @TableField("outbound_status")
    private Integer outboundStatus;

    @TableField("outbound_time")
    private String outboundTime;

    private String client;


}
