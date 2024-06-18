package org.example.back;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

public class MyBatisPlusGenerator {

    public static void main(String[] args) {
        //全局配置
        GlobalConfig config = new GlobalConfig.Builder()
                //作者
                .author("Aloar")
                // 生成路径，最好使用绝对路径，window路径是不一样的
                .outputDir("D:\\MyBatisPlusGenerator")
                // 文件覆盖
                .fileOverride()
                //设置时间对应类型
                .dateType(DateType.TIME_PACK)
                .build();

        //包名策略配置
        PackageConfig packageConfig = new PackageConfig.Builder()
                .parent("org.example.back") // 注意这里要和本项目的一致
                .mapper("mapper")
                .service("service")
                .controller("controller")
                .entity("domain")
                .xml("mapper")
                .build();

        //策略配置
        StrategyConfig strategyConfig = new StrategyConfig.Builder()
                //设置需要映射的表名
                .addInclude("wms_item")
                .addInclude("wms_container")
                .addInclude("wms_inventory")
                .addInclude("wms_inbound")
                .addInclude("wms_inbound_detail")
                .addInclude("wms_supplier")
                //设置要去掉的表名前缀
                //.addTablePrefix("huatao_")
                //策略开启⼤写命名
                .enableCapitalMode()
                .entityBuilder()
                //添加后缀
                //.formatFileName("%sDO")
                //添加lombock的getter、setter注解
                .enableLombok()
                //.enableTableFieldAnnotation()
                // 数据库表映射到实体的命名策略
                .columnNaming(NamingStrategy.underline_to_camel)
                .naming(NamingStrategy.underline_to_camel)
                .mapperBuilder()//mapper类添加@Mapper
                //生成基本的SQL片段
                .enableBaseColumnList()
                //生成基本的resultMap
                .enableBaseResultMap()
                .serviceBuilder()
                //添加后缀
                .formatServiceFileName("%sService")
                //使用restcontroller注解
                .controllerBuilder().enableRestStyle()
                .build();

        // 数据源配置
        DataSourceConfig.Builder dataSourceConfigBuilder = new DataSourceConfig
                .Builder(
                "jdbc:mysql://127.0.0.1:3307/wms?characterEncoding=utf8&useSSL=false",
                "root",
                "314159");
//                .Builder(
//                "jdbc:mysql://10.232.64.251:3306/sas?characterEncoding=utf8&useSSL=false",
//                "ganlin.li",
//                "Dispo228@scs");
//        DataSourceConfig.Builder dataSourceConfigBuilder = new DataSourceConfig.Builder(
//                "jdbc:sqlserver://127.0.0.1\\sqlexpress;databaseName=HTScan;user=sa;password=Dispo228@scs;encrypt=true;trustServerCertificate=true",
//                "sa",
//                "Dispo228@scs"
//        );
        // 创建代码生成器对象，加载配置
        AutoGenerator autoGenerator = new AutoGenerator(dataSourceConfigBuilder.build());
        autoGenerator.global(config);
        autoGenerator.packageInfo(packageConfig);
        autoGenerator.strategy(strategyConfig);

        //执行操作
        autoGenerator.execute();
        System.out.println("=======  Done 相关代码生成完毕  ========");

    }
}
