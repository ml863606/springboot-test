package com.mxl.user.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter4;

/**
 * web配置
 * 
 * @author zpy
 * @since 2018-12-29
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

	/**
	 * 设置静态资源路径
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
		super.addResourceHandlers(registry);
	}

	/**
	 * MVC转换器
	 */
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		FastJsonHttpMessageConverter4 fastJsonconverter4 = new FastJsonHttpMessageConverter4();

		// 配置属性（默认编码：UTF-8，默认支持类型：*/*）
		FastJsonConfig fastJsonConfig = new FastJsonConfig();
		fastJsonConfig.setSerializerFeatures(SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteEnumUsingToString, SerializerFeature.QuoteFieldNames,
				SerializerFeature.WriteNonStringKeyAsString);
		fastJsonconverter4.setFastJsonConfig(fastJsonConfig);

		// 覆盖默认的支持类型
		List<MediaType> supportedMediaTypes = new ArrayList<MediaType>();
		supportedMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
		fastJsonconverter4.setSupportedMediaTypes(supportedMediaTypes);

		converters.add(fastJsonconverter4);
	}

	/**
	 * 跨域问题
	 */
	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.addAllowedOrigin("*");
		corsConfiguration.addAllowedHeader("*");
		corsConfiguration.addAllowedMethod("*");

		source.registerCorsConfiguration("/**", corsConfiguration);
		return new CorsFilter(source);
	}
}
