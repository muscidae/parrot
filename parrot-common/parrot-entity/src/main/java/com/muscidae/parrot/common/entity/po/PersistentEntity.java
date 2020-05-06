package com.muscidae.parrot.common.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author muscidae
 * @date 2019/11/7 14:31
 * @copyright ©2019
 * @description 持久化实体基类
 */
@ApiModel(value = "persistent parent")
@Getter
@Setter
@Accessors(chain = true)
public abstract class PersistentEntity implements Serializable {

    protected static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "创建时间", example = "")
    @TableField("create_time")
    private Timestamp createTime;

    @ApiModelProperty(value = "修改时间")
    @TableField("update_time")
    private Timestamp updateTime;

    @ApiModelProperty(value = "标识状态,『0:正常』,『1:禁用』")
    @TableLogic
    @TableField("flag")
    private Byte flag;

    /**
     * 启用
     */
    public PersistentEntity enable(){ this.flag = 0; return this; }

    /**
     * 禁用
     */
    public PersistentEntity disable(){ this.flag = 1; return this; }

    /**
     * 检查对象是否正常
     */
    public boolean isNormal(){ return 0 == this.flag; }

}
