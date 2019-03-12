package com.gb.chrom.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.TransactionTemplate;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * @author Summer
 *
 *         2017年11月9日
 */
@Configuration
@EnableTransactionManagement
@EnableConfigurationProperties(DruidProperties.class)
@AutoConfigureBefore(DataSourceAutoConfiguration.class)
public class DruidDataSourceConfig {
	
	private static Logger logger = LoggerFactory.getLogger(DruidDataSourceConfig.class);
	
	@Autowired
	private DruidProperties druidProperties;

	@Bean
	public DataSource dataSource() throws Exception {
		DruidDataSource datasource = new DruidDataSource();
		
		datasource.setUrl(druidProperties.getUrl());
		datasource.setDriverClassName(druidProperties.getDriverClassName());
		
		try {
			datasource.setFilters(druidProperties.getFilters());
		} catch (SQLException e) {
			logger.error("Init datasource, datasource filters setting failed.", e);
		}
		
		datasource.setUsername(druidProperties.getUsername());
		datasource.setPassword(druidProperties.getPassword());
		datasource.setConnectionProperties(druidProperties.getConnectionProperties());
		datasource.setInitialSize(druidProperties.getInitialSize());
		datasource.setMinIdle(druidProperties.getMinIdle());
		datasource.setMaxWait(druidProperties.getMaxWait());
		datasource.setMaxActive(druidProperties.getMaxActive());
		
		if (null != druidProperties.getMinEvictableIdleTimeMillis()) {
			datasource.setMinEvictableIdleTimeMillis(druidProperties.getMinEvictableIdleTimeMillis());
		}
		if (null != druidProperties.getTestOnBorrow()) {
			datasource.setTestOnBorrow(druidProperties.getTestOnBorrow());
		}
		if (null != druidProperties.getTestOnReturn()) {
			datasource.setTestOnBorrow(druidProperties.getTestOnReturn());
		}
		if (null != druidProperties.getTimeBetweenEvictionRunsMillis()) {
			datasource.setTimeBetweenEvictionRunsMillis(druidProperties.getTimeBetweenEvictionRunsMillis());
		}
		if (null != druidProperties.getValidationQueryTimeout()) {
			datasource.setValidationQuery(druidProperties.getValidationQuery());
			datasource.setValidationQueryTimeout(druidProperties.getValidationQueryTimeout());
		}
		if (null != druidProperties.getLogAbandoned()) {
			datasource.setLogAbandoned(druidProperties.getLogAbandoned());
		}
		if (null != druidProperties.getRemoveAbandoned()) {
			datasource.setRemoveAbandoned(druidProperties.getRemoveAbandoned());
		}
		if (null != druidProperties.getRemoveAbandonedTimeoutMillis()) {
			datasource.setRemoveAbandonedTimeoutMillis(druidProperties.getRemoveAbandonedTimeoutMillis());
		}

		datasource.init();
		
		return datasource;
	}

	@Bean("transactionManager")
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

	@Bean("transactionTemplate")
	public TransactionTemplate transactionTemplate(DataSourceTransactionManager transactionManager) {
		return new TransactionTemplate(transactionManager);
	}

}
