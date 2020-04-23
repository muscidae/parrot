package com.muscidae.parrot.common.util.date;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

/**
 * @author muscidae
 * @date 2019/6/25 21:12
 * @description 时间转换
 */
public final class DateFormat {

    private DateFormat(){ }

    /**
     * @author muscidae
     * @data 2019/6/25 21:13
     * @description timestamp 转 LocalDateTime
     * @param timestamp
     * @return java.time.LocalDateTime
     */
    public LocalDateTime timestampToDatetime(long timestamp){
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());
    }

    /**
     * @author muscidae
     * @data 2019/6/25 21:12
     * @description LocalDataTime 转timestamp
     * @param localDateTime
     * @return long
     */
    public long datetimeToTimestamp(LocalDateTime localDateTime){
        return localDateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }

    /**
     * @author muscidae
     * @data 2019/6/26 20:10
     * @description Date 转 LocalDateTime
     * @param date
     * @return java.time.LocalDateTime
     */
    public LocalDateTime dateToLocalDateTime(Date date){
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public enum Singleton{
        INSTANCE;
        private DateFormat dateFormat;
        Singleton(){ dateFormat = new DateFormat(); }
        public DateFormat newInstance(){ return dateFormat; }
    }

}
