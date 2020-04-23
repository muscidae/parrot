package com.muscidae.parrot.common.standard.example;

import com.muscidae.parrot.common.entity.result.ApiResult;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author muscidae
 * @date 2020/4/15 18:36
 * @copyright ©2020
 * @description ExampleInterface
 */
public interface ExampleService {

    @GetMapping("/authentication")
    ApiResult<?> remoteCall();

}
