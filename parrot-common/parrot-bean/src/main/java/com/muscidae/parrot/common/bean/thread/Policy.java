package com.muscidae.parrot.common.bean.thread;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author muscidae
 * @date 2019/8/12 11:39
 * @copyright ©2019
 * @description 线程池策略
 */
@Getter
@AllArgsConstructor
public enum Policy {

    /**
     * 拒绝任务并抛出异常
     */
    ABORT_POLICY(new ThreadPoolExecutor.AbortPolicy()),

    /**
     * 拒绝任务但不做任何动作
     */
    DISCARD_POLICY(new ThreadPoolExecutor.DiscardPolicy()),

    /**
     * 拒绝任务, 并在调用者的线程中直接执行该任务
     */
    CALLER_RUNS_POLICY(new ThreadPoolExecutor.CallerRunsPolicy()),

    /**
     * 先丢弃任务队列中的第一个任务, 然后把这个任务加进队列
     */
    DISCARD_OLDEST_POLICY(new ThreadPoolExecutor.CallerRunsPolicy()),

    ;
    private RejectedExecutionHandler rejectedExecutionHandler;

}
