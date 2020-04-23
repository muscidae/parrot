package com.muscidae.parrot.common.basic.spring;

import com.muscidae.parrot.common.basic.exception.UnexpectedException;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author muscidae
 * @date 2019/1/21 11:25
 * @description 以静态变量保存Spring ApplicationContext, 可在任何代码任何地方任何时候中取出ApplicationContext.
 */
@Component
@Lazy(false)
public class SpringContextHolder implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    /**
     * @author muscidae
     * @date 2019/1/21 11:31
     * @description 从静态变量ApplicationContext中取得Bean,自动转型为所赋值对象的类型
     * @param name Bean
     * @return T
     */
    @SuppressWarnings("unchecked")
    public <T> T getBean(String name) {
        checkApplicationContext();
        return (T) applicationContext.getBean(name);
    }

    /**
     * @author muscidae
     * @date 2019/1/21 11:31
     * @description 从静态变量ApplicationContext中取得Bean,自动转型为所赋值对象的类型
     * @param clazz Bean
     * @return T
     */
    public <T> T getBean(Class<T> clazz){
        checkApplicationContext();
        return (T) applicationContext.getBean(clazz);
    }

    /**
     * @author muscidae
     * @date 2019/9/24 13:55
     * @description 指定对象类型从静态变量ApplicationContext中取得Bean
     * @param name
	 * @param clazz
     * @return T
     */
    public <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }

    /**
     * @author muscidae
     * @date 2019/1/21 11:33
     * @description 从静态变量ApplicationContext中取得Bean,自动转型为所赋值对象的类型,如果有多个Bean符合Class,取出第一个
     * @param requiredType
     * @return T
     */
    public <T> T getFirstBean(Class<T> requiredType){
        checkApplicationContext();
        Map<String,T> beans= applicationContext.getBeansOfType(requiredType);
        if(!beans.isEmpty()){
            return beans.values().iterator().next();
        }
        throw new UnexpectedException("Spring中没有类型『"+ requiredType.getName() +"』的实例");
    }

    /**
     * @author muscidae
     * @date 2019/1/21 11:32
     * @description 取得存储在静态变量中的ApplicationContext.
     * @return org.springframework.context.ApplicationContext
     */
    public ApplicationContext getApplicationContext() {
        checkApplicationContext();
        return applicationContext;
    }

    /**
     * @author muscidae
     * @date 2019/1/21 11:30
     * @description 清除applicationContext
     * @return void
     */
    public void cleanApplicationContext(){
        applicationContext = null;
    }

    /**
     * @author muscidae
     * @date 2019/1/21 11:29
     * @description ApplicationContext非空检测
     * @param
     * @return void
     */
    private void checkApplicationContext() {
        if (null == applicationContext) {
            throw new IllegalStateException("ApplicationContext未注入, 请定义SpringContextHolder");
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
