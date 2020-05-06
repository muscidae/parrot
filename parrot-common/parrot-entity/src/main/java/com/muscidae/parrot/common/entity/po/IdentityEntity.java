package com.muscidae.parrot.common.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author muscidae
 * @date 2019/11/8 01:11
 * @copyright ©2019
 * @description ID 基类
 */
@ApiModel(value = "id parent")
@Getter
@Setter
@Accessors(chain = true)
public abstract class IdentityEntity<ID> extends PersistentEntity implements Serializable {

    protected static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private ID id;

}
