package com.mxl.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 *
 * @author zpy
 * @since 2018-12-29
 */
@SpringBootApplication
public class SpringBootTestApplication extends SpringBootServletInitializer {
	private static final Logger LOG = LoggerFactory.getLogger(SpringBootTestApplication.class);

	// 外部容器启动类
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {

		LOG.info("========= 开始启动（外部容器方式）...  ===========");

		return builder.sources(SpringBootTestApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootTestApplication.class, args);

		LOG.info("========= SpringBoot-test 已启动！ ===========");
	}
}
