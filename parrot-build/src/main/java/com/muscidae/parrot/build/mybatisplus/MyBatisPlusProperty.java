package com.muscidae.parrot.build.mybatisplus;

import com.baomidou.mybatisplus.annotation.DbType;
import lombok.Getter;
import lombok.Setter;

/**
 * @author muscidae
 * @date 2018/12/16 10:44
 * @copyright ©2019
 * @description 代码生成配置类
 */
@Getter
@Setter
class MyBatisPlusProperty {

    /**
     * 包名
     */
    private String packageName;

    /**
     * 模块名
     */
    private String modelName;

    /**
     * 逻辑删除字段名称
     */
    private String flag;

    /**
     * 模块路径
     * freemarker模板引擎:『"/templates/mapper.xml.ftl"』
     * velocity模板引擎:『"/templates/mapper.xml.vm"』
     */
    private String templatePath ;

    /**
     * 表名
     */
    private String tableName;

    /**
     * 数据库表名前缀
     */
    private String tableNamePrefix;

    /**
     * 数据库url
     * 『jdbc:mysql://localhost:3306/ares_muscidae?useUnicode=true&useSSL=false&serverTimezone=Asia/Shanghai&characterEncoding=utf8』
     */
    private String url;

    /**
     * 数据库userName
     * 『root』
     */
    private String userName;

    /**
     * 数据库passWord
     * 『root』
     */
    private String passWord;

    /**
     * 数据库DriverName
     * 『com.mysql.cj.jdbc.Driver』
     */
    private String driverName;

    /**
     * 数据库类型
     * MyBatisPlus枚举类
     * 『DbType.MYSQL』
     */
    private DbType dbType;

    /**
     * 作者
     */
    private String author;

    /**
     * Mapper存放路径
     */
    private String mapperPath;

    /**
     * 通用Controller 路径
     */
    private String superControllerClass;


    /**
     * 模板引擎配置路径
     */
    interface TemplatePath{
        /**
         * Freemarker模板引擎配置路径
         */
        String FreemarkerPath = "/templates/mapper.xml.ftl";

        /**
         * VelocityPath模板引擎配置路径
         */
        String VelocityPath = "/templates/mapper.xml.vm";
    }

}

