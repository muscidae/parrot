package com.muscidae.parrot.common.basic.convert;

import org.springframework.beans.BeanUtils;

/**
 * @author muscidae
 * @date 2019/5/29 11:00
 * @description 属性拷贝工具类
 */
public class PropertiesCopyUtils {

    private PropertiesCopyUtils(){ }

    public <Target> Target copyProperties(Object source, Class<Target> targetClazz){
        if (null == source || null == targetClazz) return null;
        Target target;
        try {
            target = targetClazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            return null;
        }
        BeanUtils.copyProperties(source, target);
        return target;
    }

    public enum Singleton{
        INSTANCE;
        private PropertiesCopyUtils propertiesCopyUtils;
        Singleton(){ propertiesCopyUtils = new PropertiesCopyUtils(); }
        public PropertiesCopyUtils newInstance(){ return propertiesCopyUtils; }
    }

}
