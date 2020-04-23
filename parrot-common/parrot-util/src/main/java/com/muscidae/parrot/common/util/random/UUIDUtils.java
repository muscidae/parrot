package com.muscidae.parrot.common.util.random;

import java.util.UUID;

/**
 * @author muscidae
 * @date 2019/5/27 18:58
 * @description UUID工具类
 */
public final class UUIDUtils {

    private UUIDUtils(){ }

    /**
     * @author muscidae
     * @date 2019/5/27 18:59
     * @description 去除 UUID 中的"-", 生成小写UUID, 长度为32位
     * @return UUID
     */
    public String generate(){
        return UUID.randomUUID().toString().replace("-", "").toLowerCase();
    }

    public enum Singleton{
        INSTANCE;
        private UUIDUtils uuidUtils;
        Singleton(){ uuidUtils = new UUIDUtils(); }
        public UUIDUtils newInstance(){ return uuidUtils; }
    }

}
