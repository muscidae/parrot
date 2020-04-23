package com.muscidae.parrot.build.mybatisplus;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author muscidae
 * @date 2018/12/15 12:13
 * @copyright ©2019
 * @description 『MybatisPlus代码生成』执行 main 方法
 */
@Slf4j
public class MyBatisPlusGenerator {

    //项目路径
    private static final String projectPath = System.getProperty("user.dir");

    //代码生成配置
    private static final MyBatisPlusProperty properties;

    static{
        properties = new MyBatisPlusProperty();
        properties.setAuthor("muscidae");
        properties.setPackageName("com.muscidae.parrot.");
        properties.setTemplatePath(MyBatisPlusProperty.TemplatePath.FreemarkerPath);

        properties.setDbType(DbType.MYSQL);
        properties.setUrl("jdbc:mysql://localhost:3306/parrot_pay?useUnicode=true&useSSL=false&serverTimezone=Asia/Shanghai&characterEncoding=utf8");
        properties.setDriverName("com.mysql.cj.jdbc.Driver");
        properties.setUserName("root");
        properties.setPassWord("");

//        properties.setDbType(DbType.ORACLE);
//        properties.setUrl("jdbc:oracle:thin:@112.16.211.200:8007:orcl");
//        properties.setDriverName("oracle.jdbc.driver.OracleDriver");
//        properties.setUserName("qzaj");
//        properties.setPassWord("qzaj");

        properties.setModelName("team");
        properties.setTableNamePrefix("su_");
        properties.setTableName("su_team");

        properties.setFlag("flag");
        properties.setSuperControllerClass("com.muscidae.parrot.common.controller.BaseController");
    }

    /**
     * @author muscidae
     * @date 2019/9/11 13:55
     * @description 代码生成方法
     * @param args
     * @return void
     */
    public static void main(String[] args) {
        PackageConfig packageConfig = getPackageConfig(properties.getPackageName(), properties.getModelName());
        new AutoGenerator()
                .setCfg( getInjectionConfig( getFileOutConfigList( packageConfig ) ) )  // 『自定义配置』
                .setPackageInfo( packageConfig )                                        // 『包名配置』
                .setDataSource( getDataSourceConfig() )                                 // 『数据源配置』
                .setGlobalConfig( getGlobalConfig() )                                   // 『全局配置』
                .setStrategy( getStrategyConfig() )                                     // 『配置策略』
                .setTemplateEngine( new FreemarkerTemplateEngine() )                    // 『模板引擎』
//                .setTemplate( getTemplateConfig() )                                     // 『配置模板』
                .execute()                                                              // 『执行生成代码程序』
        ;
    }

    /**
     * @author muscidae
     * @date 2019/9/11 14:25
     * @description 『生成代码目录路径』
     * @param
     * @return java.lang.String
     */
    private static String getCodeGeneratorPath(){
        File codeFolder = new File(projectPath + "/code-generating");
        if(!codeFolder.exists()) {
            codeFolder.mkdir();
        }
        return codeFolder.getPath();
    }

    /**
     * @author muscidae
     * @date 2019/9/11 13:55
     * @description 『自定义配置』
     * @param fileOutConfigList
     * @return com.baomidou.mybatisplus.generator.InjectionConfig
     */
    private static InjectionConfig getInjectionConfig(List<FileOutConfig> fileOutConfigList){
        return new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        }.setFileOutConfigList(fileOutConfigList);
    }

    /**
     * @author muscidae
     * @date 2019/9/11 13:54
     * @description 『配置模板』
     * @return com.baomidou.mybatisplus.generator.config.TemplateConfig
     */
    private static TemplateConfig getTemplateConfig(){
        // 自定义输出模板
        return new TemplateConfig()
                .setXml(null)
//                .setEntity()
//                .setService()
//                .setController()
                ;
    }

    /**
     * @author muscidae
     * @date 2019/9/11 13:54
     * @description 『全局配置』
     * @return com.baomidou.mybatisplus.generator.config.GlobalConfig
     */
    private static GlobalConfig getGlobalConfig(){
        return new GlobalConfig()
                .setOutputDir(getCodeGeneratorPath())   // 生成代码存放路径
                .setOpen(false)                         // 生成代码后打开该文件目录
                .setFileOverride(true)                  // 生成的代码文件是否覆盖
                .setAuthor(properties.getAuthor())      // 设置作者名称
                .setEnableCache(false)                  // 是否开启二级缓存
                .setBaseResultMap(true)                 // XML是否生成 ResultMap
                .setBaseColumnList(true)                // XML是否生成 ColumnList
                .setActiveRecord(true)                  // 实体是否启用AR模式
                .setDateType(DateType.ONLY_DATE)        // 生成实体类使用的时间包类型
                ;
    }

    /**
     * @author muscidae
     * @date 2019/9/11 13:54
     * @description 『数据源配置』
     * @return com.baomidou.mybatisplus.generator.config.DataSourceConfig
     */
    private static DataSourceConfig getDataSourceConfig(){
        return new DataSourceConfig()
                .setDbType(properties.getDbType())          // 数据库类型
                .setUrl(properties.getUrl())                // 数据库连接协议及地址
                .setDriverName(properties.getDriverName())  // 驱动类
                .setUsername(properties.getUserName())      // 用户名
                .setPassword(properties.getPassWord())      // 密码
                ;
    }

    /**
     * @author muscidae
     * @date 2019/9/11 13:53
     * @description 『配置策略』
     * @return com.baomidou.mybatisplus.generator.config.StrategyConfig
     */
    private static StrategyConfig getStrategyConfig(){
        return new StrategyConfig()
                .setNaming(NamingStrategy.underline_to_camel)                   // 表名生成策略
                .setLogicDeleteFieldName(properties.getFlag())                  // 逻辑删除属性名称
                .setEntityLombokModel(true)                                     // Lombok注解模型
                .setRestControllerStyle(true)                                   // RestController注解
                .setEntityBooleanColumnRemoveIsPrefix(true)                     // 移除数据库字段前缀
                .setTablePrefix(properties.getTableNamePrefix())                // 表名前缀
                .setInclude(properties.getTableName())                          // 表名
                ;
//                .setSuperControllerClass(properties.getSuperControllerClass())  // 3.3.0 @deprecated 通用Controller路径
    }

    /**
     * @author muscidae
     * @date 2019/9/11 13:54
     * @description 『包名配置』
     * @param packageName
	 * @param moduleName
     * @return com.baomidou.mybatisplus.generator.config.PackageConfig
     */
    private static PackageConfig getPackageConfig(String packageName,String moduleName){
        return new PackageConfig()
                .setParent(packageName)     //包名
                .setModuleName(moduleName)  //模块名
                ;
    }

    /**
     * @author muscidae
     * @date 2019/9/11 13:54
     * @description 『输出配置』
     * @param packageConfig
     * @return java.util.List<com.baomidou.mybatisplus.generator.config.FileOutConfig>
     */
    private static List<FileOutConfig> getFileOutConfigList(PackageConfig packageConfig){
        // 自定义输出配置
        List<FileOutConfig> fileOutConfigList = new ArrayList<>();
        // 自定义配置会被优先输出
        fileOutConfigList.add(new FileOutConfig(properties.getTemplatePath()) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名
                return projectPath + properties.getMapperPath() + packageConfig.getModuleName()
                        + StringPool.SLASH + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        return fileOutConfigList;
    }

}

