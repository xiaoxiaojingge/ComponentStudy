package com.itjing;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;

//代码自动生成器
public class MyGenerator {

	public static void main(String[] args) {
		// 需要构建一个代码自动生成器对象
		AutoGenerator mpg = new AutoGenerator();
		// 配置策略
		// 1.全局配置
		GlobalConfig gc = new GlobalConfig();
		String projectPath = System.getProperty("user.dir");
		gc.setOutputDir(projectPath + "/src/main/java");
		gc.setAuthor("jinggege");
		gc.setOpen(false);// 是否打开资源管理器，生成的文件夹
		gc.setFileOverride(false); // 是否覆盖     
		gc.setServiceName("%sService"); // 去Service的I前缀        
		gc.setIdType(IdType.ID_WORKER);
		gc.setDateType(DateType.ONLY_DATE);
		// gc.setSwagger2(true);
		mpg.setGlobalConfig(gc);

		// 2、设置数据源
		DataSourceConfig dsc = new DataSourceConfig();
		dsc.setUrl("jdbc:mysql://localhost:3306/mybatis_plus?useSSL=false&useUnicode=true&characterEncoding=utf-8");
		dsc.setDriverName("com.mysql.jdbc.Driver");
		dsc.setUsername("root");
		dsc.setPassword("root");
		dsc.setDbType(DbType.MYSQL);
		mpg.setDataSource(dsc);

		// 3、包的配置
		PackageConfig pc = new PackageConfig();
		pc.setModuleName("mybatisplus");// 模块名，是一个包
		pc.setParent("com.itjing");
		pc.setEntity("entity");
		pc.setMapper("mapper");
		pc.setService("service");
		pc.setController("controller");
		mpg.setPackageInfo(pc);

		// 4、策略配置
		StrategyConfig strategy = new StrategyConfig();
		strategy.setInclude("user"); // 设置要映射的表名，可以写多个，用逗号分隔，自己根据需要修改
		strategy.setNaming(NamingStrategy.underline_to_camel);
		strategy.setColumnNaming(NamingStrategy.underline_to_camel);
		strategy.setEntityLombokModel(true); // 自动lombok；
		strategy.setLogicDeleteFieldName("deleted");// 逻辑删除字段，自己根据需要修改

		// 自动填充配置        
		TableFill gmtCreate = new TableFill("create_time", FieldFill.INSERT);// 自己根据需要修改，数据库字段名
		TableFill gmtModified = new TableFill("update_time", FieldFill.INSERT_UPDATE);// 自己根据需要修改，数据库字段名
		ArrayList<TableFill> tableFills = new ArrayList<>();
		tableFills.add(gmtCreate);
		tableFills.add(gmtModified);
		strategy.setTableFillList(tableFills);
		// 乐观锁        
		strategy.setVersionFieldName("version");// 自己根据需要修改
		strategy.setRestControllerStyle(true);
		strategy.setControllerMappingHyphenStyle(true); // localhost:8080/hello_id_2      
														//  
		mpg.setStrategy(strategy);

		mpg.execute(); // 执行
	}

}
