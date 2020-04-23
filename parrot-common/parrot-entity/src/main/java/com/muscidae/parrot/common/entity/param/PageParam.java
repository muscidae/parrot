package com.muscidae.parrot.common.entity.param;

import com.muscidae.parrot.common.entity.validator.VerifyGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Past;
import java.sql.Timestamp;

/**
 * @author muscidae
 * @date 2020/2/19 16:52
 * @copyright ©2020
 * @description
 */
@ApiModel(value = "分页条件查询")
@Getter
@Setter
public class PageParam {

    @ApiModelProperty(value = "页码,『默认:1』")
    @Min(value = 1, message = "页码最小为1", groups = {VerifyGroup.Retrieve.class})
    protected Integer page = 1;

    @ApiModelProperty(value = "一页显示几行数据,『默认:10』")
    @Min(value = 1, message = "每页显示数据最少为1", groups = {VerifyGroup.Retrieve.class})
    protected Integer size = 10;

    @ApiModelProperty(value = "排序,『0:降序』,『1:升序』 默认降序")
    @Max(value = 1, message = "排序参数有误", groups = {VerifyGroup.Retrieve.class})
    private byte sort;

    @ApiModelProperty(value = "开始时间")
    @Past(message = "开始时间必须为过去的时间", groups = {VerifyGroup.Retrieve.class})
    private Timestamp beginTime;

    @ApiModelProperty(value = "结束时间")
    @Past(message = "结束时间必须为过去的时间", groups = {VerifyGroup.Retrieve.class})
    private Timestamp endTime;

}
