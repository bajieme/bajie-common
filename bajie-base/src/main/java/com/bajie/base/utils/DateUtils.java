package com.bajie.base.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * 日期工具类
 *
 * @author bajie
 * @date 2022-05-02 3:02 下午
 * @since 1.0.0
 */
public class DateUtils {

    private static final String DATE_PATTERN_DEFAULT = "yyyy-MM-dd HH:mm:ss";

    private static final String DATE_PATTERN_DEFAULT_PLUS = "yyyy-MM-dd HH:mm:ss:SSS";

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN_DEFAULT);

    private static final DateTimeFormatter DATE_TIME_FORMATTER_PLUS = DateTimeFormatter.ofPattern(DATE_PATTERN_DEFAULT_PLUS);

    /**
     * 获取时间
     *
     * @param milliSecond 毫秒
     * @return LocalDateTime
     */
    public static LocalDateTime parseMilli(long milliSecond) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(milliSecond), ZoneId.systemDefault());
    }

    /**
     * 格式化时间
     *
     * @param milliSecond 毫秒
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String formatMilli(long milliSecond) {
        return DATE_TIME_FORMATTER.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(milliSecond), ZoneId.systemDefault()));
    }

    /**
     * 格式化时间
     *
     * @param milliSecond 毫秒
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String formatMilliPlus(long milliSecond) {
        return DATE_TIME_FORMATTER_PLUS.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(milliSecond), ZoneId.systemDefault()));
    }

    /**
     * 格式化时间
     *
     * @param second 秒
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String formatSecond(long second) {
        return DATE_TIME_FORMATTER.format(LocalDateTime.ofInstant(Instant.ofEpochSecond(second), ZoneId.systemDefault()));
    }

    /**
     * 格式化时间
     *
     * @param second 秒
     * @return yyyy-MM-dd HH:mm:ss:SSS
     */
    public static String formatSecondPlus(long second) {
        return DATE_TIME_FORMATTER_PLUS.format(LocalDateTime.ofInstant(Instant.ofEpochSecond(second), ZoneId.systemDefault()));
    }

    /**
     * 获取时间
     *
     * @param second 秒
     * @return LocalDateTime
     */
    public static LocalDateTime parseSecond(long second) {
        return LocalDateTime.ofInstant(Instant.ofEpochSecond(second), ZoneId.systemDefault());
    }

    /**
     * 获取秒数
     *
     * @param localDateTime 时间
     * @return 秒数
     */
    public static long getSecond(LocalDateTime localDateTime) {
        // 获取秒数
        return localDateTime.toEpochSecond(ZoneOffset.of("+8"));
    }


    /**
     * 获取秒数
     *
     * @param localDateTime 时间
     * @return 获取毫秒数
     */
    public static long getMilli(LocalDateTime localDateTime) {
        return localDateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }
}
