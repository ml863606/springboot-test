package com.mxl.user.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.mapper.ISqlInjector;
import com.baomidou.mybatisplus.mapper.LogicSqlInjector;
import com.baomidou.mybatisplus.plugins.PerformanceInterceptor;
import com.baomidou.mybatisplus.spring.MybatisMapperRefresh;
import com.mxl.user.config.properties.DruidProperties;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.util.Assert;

import java.io.IOException;

/**
 * MybatisPlus配置
 * 
 * @author zpy
 */
@Configuration
@MapperScan(basePackages = { "com.wxadt.wxcep.onecard.mapper.*" })
public class MybatisPlusConfig {
	private static Logger LOG = LoggerFactory.getLogger(MybatisPlusConfig.class);

	@Autowired
	private DruidProperties druidProperties;

	/**
	 * mybatis-plus SQL执行效率插件【生产环境可以关闭】
	 */
	@Bean
	@Profile({ "local", "dev" })
	public PerformanceInterceptor performanceInterceptor() {
		return new PerformanceInterceptor();
	}

	/**
	 * xml热加载
	 */
	@Bean
	@Profile({ "local" })
	public MybatisMapperRefresh mybatisMapperRefresh(SqlSessionFactory sessionFactory) throws IOException {
		LOG.info("Mybatis-plus configuration mybatisMapperRefresh...");

		// 加载配置文件
		PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();
		Resource[] resources = pathMatchingResourcePatternResolver.getResources("classpath*:com/wxadt/wxcep/mapper/*/sql/*.xml");

		MybatisMapperRefresh mybatisMapperRefresh = new MybatisMapperRefresh(resources, sessionFactory, true);
		return mybatisMapperRefresh;
	}

	/**
	 * 注入sql注入器
	 */
	@Bean
	public ISqlInjector sqlInjector() {
		return new LogicSqlInjector();
	}

	/**
	 * 单数据源连接池配置（默认单数据源）
	 */
	@Bean
	@ConditionalOnExpression("#{!environment.containsProperty('spring.ds_slave.config.open')}")
	public DruidDataSource singleDatasource() {
		return dataSource();
	}

	// 数据源
	private DruidDataSource dataSource() {
		Assert.notNull(druidProperties.getUrl(), "can't find datasource configuration file");
		LOG.info("start initialization singleDatasource server, properties: {}", druidProperties);

		DruidDataSource dataSource = new DruidDataSource();
		druidProperties.config(dataSource);
		return dataSource;
	}

}
