package com.gb.chrom;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@SpringBootApplication

public class Application extends SpringBootServletInitializer {
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return configureApplication(builder);
	}

	private static SpringApplicationBuilder configureApplication(SpringApplicationBuilder builder) {
		return builder.sources(Application.class).bannerMode(Banner.Mode.OFF);
	}
	
	
	public static final String SYS_CONFIG_CACHE_ID = "sys-config-cache-id";
	public static final String SPM_SERIAL_NO_RULE_ID = "specimen-serial-rule-id";
	public static final String USER_MESSAGE_CACHE_ID = "user-message-cache-id";
	
	
	public static final String _SYSTEM_CONFIG_ID = "_sysConfig";
	
}
