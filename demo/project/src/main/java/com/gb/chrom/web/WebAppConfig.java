package com.gb.chrom.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.MultipartConfigElement;

import org.apache.commons.lang3.StringUtils;
import org.sitemesh.SiteMeshContext;
import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;
import org.sitemesh.content.ContentProperty;
import org.sitemesh.content.tagrules.TagRuleBundle;
import org.sitemesh.content.tagrules.html.ExportTagToContentRule;
import org.sitemesh.tagprocessor.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.RegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.planetj.servlet.filter.compression.CompressingFilter;

/**
 * @author Summer
 *
 *         2017年11月10日
 */
@Configuration
public class WebAppConfig implements WebMvcConfigurer {

	@Autowired
	private WebSessionInterceptor webSessionInterceptor;
	@Autowired
	private WebConfigInterceptor webConfigInterceptor;
	
	@Value("${static.resources.path}")
	private String ress;
	@Autowired
	private MultipartConfigElement multipartConfigElement;

	@Override
	public void addInterceptors(final InterceptorRegistry registry) {
		registry.addInterceptor(webSessionInterceptor).addPathPatterns("/", "/**/*.html");
		registry.addInterceptor(webConfigInterceptor).addPathPatterns("/", "/**/*.html").excludePathPatterns("/config/setting.html");
	}

	@Bean
	public RegistrationBean compressingFilter() {
		FilterRegistrationBean<CompressingFilter> filterRegistration = new FilterRegistrationBean<CompressingFilter>();
		filterRegistration.setFilter(new CompressingFilter());
		filterRegistration.setName("compressingFilter");
		filterRegistration.setAsyncSupported(true);
		filterRegistration.addUrlPatterns("*.css,*.js,*.html,*.json");

		Map<String, String> initParameters = new HashMap<String, String>();
		initParameters.put("includeContentTypes", "text/html,text/xml,text/javascript,text/css,text/plain");
		filterRegistration.setInitParameters(initParameters);

		return filterRegistration;
	}

	@Bean
	public FilterRegistrationBean<TemplateSiteMeshFilter> templateSitemeshFilter() {
		FilterRegistrationBean<TemplateSiteMeshFilter> filterRegistrationBean = new FilterRegistrationBean<TemplateSiteMeshFilter>();
		filterRegistrationBean.setFilter(new TemplateSiteMeshFilter());
		filterRegistrationBean.addUrlPatterns("/*");
		filterRegistrationBean.setEnabled(true);
		return filterRegistrationBean;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		String location = multipartConfigElement.getLocation();
		location = location.endsWith("/") ? location : location + "/";
		
		registry.addResourceHandler(ress + "/**").addResourceLocations("file:" + location);
	}

}

class TemplateSiteMeshFilter extends ConfigurableSiteMeshFilter {

	@Override
	protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
		builder.addDecoratorPath("/**", "/decorator.dec").addExcludedPath("*/login.html").addExcludedPath("/analysis/report/info/*.html");
		builder.addTagRuleBundle(new CustomTagRuleBundle());
	}

}

class CustomTagRuleBundle implements TagRuleBundle {
	@Override
	public void install(State defaultState, ContentProperty contentProperty, SiteMeshContext siteMeshContext) {
		defaultState.addRule("imports", new ExportTagToContentRule(siteMeshContext, contentProperty.getChild("imports"), true));
	}

	@Override
	public void cleanUp(State defaultState, ContentProperty contentProperty, SiteMeshContext siteMeshContext) {
	}

}
