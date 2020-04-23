package com.muscidae.parrot.gateway.controller;

import com.muscidae.parrot.common.entity.result.ApiResult;
import com.muscidae.parrot.common.entity.result.ResultStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author muscidae
 * @date 2019/6/17 14:02
 * @description 熔断控制层
 */
@RestController
public class FallbackController {

    @RequestMapping("/fallback")
    public ApiResult<?> fallback() {
        return ApiResult.fail(ResultStatus.FUSING.getStatus(), ResultStatus.FUSING.getMessage());
    }

}
