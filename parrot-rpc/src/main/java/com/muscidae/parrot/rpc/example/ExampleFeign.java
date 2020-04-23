package com.muscidae.parrot.rpc.example;

import com.muscidae.parrot.common.constant.ServerConst;
import com.muscidae.parrot.common.entity.result.ApiResult;
import com.muscidae.parrot.common.standard.example.ExampleService;
import feign.hystrix.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * @author muscidae
 * @date 2020/4/15 18:46
 * @copyright ©2020
 * @description
 */
@FeignClient(name = ServerConst.Alias.PARROT_EXAMPLE, fallbackFactory = ExampleFeign.Fallback.class)
public interface ExampleFeign extends ExampleService {
    @Component
    class Fallback implements FallbackFactory<ExampleFeign> {
        @Override
        public ExampleFeign create(Throwable throwable) {
            return ApiResult::error;
        }
    }
}
