package com.muscidae.parrot.common.basic.controller;

import com.muscidae.parrot.common.basic.exception.BusinessException;
import com.muscidae.parrot.common.entity.result.ResultStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author muscidae
 * @date 2018/12/15 18:31
 * @copyright ©2019
 * @description 通用Controller,request,response初始化
 */
public abstract class BaseController {

    protected final Logger log = LoggerFactory.getLogger(BaseController.class);

    protected HttpServletRequest request;

    protected HttpServletResponse response;

    /**
     * @author muscidae
     * @date 2018/12/15 18:37
     * @description 初始化属性,在每个方法执行前执行该赋值方法
     * @param request
	 * @param response
     * @return void
     */
    @ModelAttribute
    public void setAttribute(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }


    /**
     * @author muscidae
     * @date 2019/10/16 3:27
     * @description 获取PrintWriter对象
     * @return java.io.PrintWriter
     */
    protected PrintWriter getWriter(){
        PrintWriter writer = null;
        try {
            return writer = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
            log.error("获取PrintWriter对象异常, 异常信息:『{}』", e.getMessage());
        } finally {
            if (null != writer)
                writer.flush();
        }
        return null;
    }

    /**
     * @author muscidae
     * @date 2019/10/16 3:27
     * @description 获取PrintWriter对象
     * @return java.io.PrintWriter
     */
    protected ServletInputStream getInputStream(){
        try {
            return request.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
            log.error("获取ServletInputStream对象异常, 异常信息:『{}』", e.getMessage());
        }
        return null;
    }

    /**
     * @author muscidae
     * @date 2020/2/14 11:51
     * @description 表单校验
     * @param result 绑定结果
     */
    protected void formValidation(BindingResult result){
        if (result.hasErrors())
            throw new BusinessException(null == result.getFieldError() ?
                    ResultStatus.FAIL.getMessage() : result.getFieldError().getDefaultMessage());
    }


}
