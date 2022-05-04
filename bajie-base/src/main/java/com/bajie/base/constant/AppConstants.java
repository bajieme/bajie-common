package com.bajie.base.constant;

/**
 * 微服务系统常量
 *
 * @author bajie
 * @date 2022-05-04 10:49 上午
 * @since 1.0.0
 */
public interface AppConstants {

    /**
     * 应用版本
     */
    String APPLICATION_VERSION = "1.0.0";

    /**
     * 基础包
     */
    String BASE_PACKAGES = "com.bajie";

    /**
     * 应用名前缀
     */
    String APPLICATION_NAME_PREFIX = "";

    /**
     * 网关模块名称
     */
    String APPLICATION_GATEWAY_NAME = APPLICATION_NAME_PREFIX + "gateway";

    /**
     * 授权模块名称
     */
    String APPLICATION_AUTH_NAME = APPLICATION_NAME_PREFIX + "auth";

    /**
     * 系统模块名称
     */
    String APPLICATION_SYSTEM_NAME = APPLICATION_NAME_PREFIX + "system";

    /**
     * demo模块名称
     */
    String APPLICATION_DEMO_NAME = APPLICATION_NAME_PREFIX + "demo";

    /**
     * 测试模块名称
     */
    String APPLICATION_TEST_NAME = APPLICATION_NAME_PREFIX + "test";

    /**
     * 开发环境
     */
    String DEV_CODE = "dev";

    /**
     * 测试环境
     */
    String TEST_CODE = "test";

    /**
     * 生产环境
     */
    String PROD_CODE = "prod";

    /**
     * 代码部署于 linux 上，工作默认为 mac 和 Windows
     */
    String OS_NAME_LINUX = "LINUX";
}
