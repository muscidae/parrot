package com.muscidae.parrot.common.constant.producer;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author muscidae
 * @date 2019/8/12 14:06
 * @copyright ©2019
 * @description
 */
public class ProducerConst {

    @Getter
    @AllArgsConstructor
    public enum Type{
        /**
         * 同步
         */
        SYNC("sync"),
        /**
         * 异步
         */
        ASYNC("async"),
        ;
        private String type;
    }


    @Getter
    @AllArgsConstructor
    public enum Pattern{
        // region Rabbit
        /**
         * 点对点模式
         */
        DIRECT("direct"),
        /**
         * 主题模式
         */
        TOPIC("topic"),
        /**
         * 发布/订阅模式
         */
        FANOUT("fanout"),
        /**
         * 消息头模式
         */
        HEADERS("headers"),
        // endregion

        // region Rocket
        /**
         * 有序模式
         */
        ORDER("order"),
        /**
         * 发布/订阅模式
         */
        BROADCASTING("broadcasting"),
        /**
         * 延迟模式
         */
        SCHEDULE("schedule"),
        /**
         * 批量模式
         */
        BATCH("batch"),
        /**
         * 过滤器模式
         */
        FILTER("filter"),
        /**
         * 日志模式
         */
        LOGAPPENDER("logappender"),
        /**
         *
         */
        OPENMESSAGING("openmessaging"),

        /**
         * 事务模式
         */
        TRANSACTION("transaction"),

        // endregion
        ;
        private String pattern;
    }

}
