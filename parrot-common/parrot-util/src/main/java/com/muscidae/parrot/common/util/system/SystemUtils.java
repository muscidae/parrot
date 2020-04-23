package com.muscidae.parrot.common.util.system;

import java.lang.management.ManagementFactory;

/**
 * @author muscidae
 * @date 2020/4/12 21:54
 * @copyright ©2020
 * @description 系统工具类
 */
public class SystemUtils {

    /**
     * 获取正在运行的Java虚拟机进程ID
     */
    public long getPid() {
        return ManagementFactory.getRuntimeMXBean().getPid();
    }



}
