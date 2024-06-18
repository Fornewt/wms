package org.example.back.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author ganlin.li
 * @since 2024-06-06
 */
@Data // 它会自动生成所有字段的getter和setter方法，还会生成toString、equals和hashCode方法
@EqualsAndHashCode(callSuper = false) // 生成equals和hashCode方法，不调用父类的方法
public class SysUser implements Serializable {// 继承使该类可以序列化


    @TableId(value = "id", type = IdType.AUTO) // 主键
    private Integer id;

    /**
     * 中文用户名
     */
    @TableField("user_name")
    private String userName;

    /**
     * 密码
     */
    private String password;

    @TableField("created_time")
    private LocalDateTime createdTime;

    @TableField("created_by")
    private String createdBy;

    @TableField("update_time")
    private LocalDateTime updateTime;

    @TableField("update_by")
    private LocalDateTime updateBy;


}
