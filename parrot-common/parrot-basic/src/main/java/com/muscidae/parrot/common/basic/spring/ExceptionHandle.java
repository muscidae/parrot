package com.muscidae.parrot.common.basic.spring;

import com.muscidae.parrot.common.basic.exception.BusinessException;
import com.muscidae.parrot.common.basic.exception.UnexpectedException;
import com.muscidae.parrot.common.entity.result.ApiResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/**
 * @author musicdae
 * @date 2019/1/22 12:27
 * @description 全局异常处理
 */
@RestControllerAdvice
public class ExceptionHandle {

	private final Logger log = LoggerFactory.getLogger(ExceptionHandle.class);

	@ExceptionHandler(BusinessException.class)
	public @ResponseBody ApiResult<?> processException(BusinessException e){
	    log.error("业务异常:『{}』", e.getMessage(), e);
	    return ApiResult.fail(e.getMessage());
	}

	@ExceptionHandler(UnexpectedException.class)
	public @ResponseBody ApiResult<?> processException(UnexpectedException e){
		log.error("非预期异常:『{}』", e.getMessage(), e);
		return ApiResult.fail(e.getMessage());
	}
	
	/**
	 * 全局异常处理
	 */
	@ExceptionHandler(Exception.class)
	public @ResponseBody ApiResult<?> processException(Exception e){
	    log.error("全局异常:『{}』", e.getMessage(), e);
	    return ApiResult.error(e.getMessage());
	}

}
