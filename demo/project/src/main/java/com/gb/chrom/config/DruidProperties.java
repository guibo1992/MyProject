package com.gb.chrom.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import com.alibaba.druid.pool.DruidAbstractDataSource;

/**
 * Datasource configuration properties for druid.
 *
 * @author Summer
 */
@ConfigurationProperties(prefix = DruidProperties.DRUID_PREFIX)
public class DruidProperties implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	public static final String DRUID_PREFIX = "druid";

	/**
	 * 连接数据库的 URL
	 */
	private String url;

	/**
	 * 连接数据库的用户名
	 */
	private String username;

	/**
	 * 连接数据库的密码, 在ConnectionProperties中指定config.decrypt=true对密码加密, config.decrypt.key可指定加密公钥
	 */
	private String password;

	/**
	 * 驱动名称, 可选配置
	 */
	private String driverClassName;

	/**
	 * 属性类型是字符串，通过别名的方式配置扩展插件，常用的插件有： 监控统计filter:stat 日志filter:log4j 防御sql注入filter:wall, filter:config等
	 */
	private String filters;

	/**
	 * 连接属性, 可配置config.decrypt, config.decrypt.key等属性
	 */
	private String connectionProperties;

	/**
	 * 初始化时建立物理连接的个数。初始化发生在显示调用init方法，或者第一次getConnection时
	 */
	private Integer initialSize;

	/**
	 * 最大连接池数量
	 */
	private Integer maxActive;

	/**
	 * 最小连接池数量
	 */
	private Integer minIdle;

	/**
	 * 获取连接时最大等待时间，单位毫秒。配置了maxWait之后，缺省启用公平锁，并发效率会有所下降，如果需要可以通过配置useUnfairLock属性为true使用非公平锁。
	 */
	private Long maxWait;

	/**
	 * 有两个含义：<br>
	 * <p>
	 * 1) Destroy线程会检测连接的间隔时间，如果连接空闲时间大于等于minEvictableIdleTimeMillis则关闭物理连接。
	 * </p>
	 * <p>
	 * 2) testWhileIdle的判断依据，详细看testWhileIdle属性的说明
	 * </p>
	 */
	private Long timeBetweenEvictionRunsMillis;

	/**
	 * 连接保持空闲而不被驱逐的最长时间
	 */
	private Long minEvictableIdleTimeMillis;

	/**
	 * 是否缓存preparedStatement，也就是PSCache。PSCache对支持游标的数据库性能提升巨大，比如说oracle, mysql或分库分表较多的数据库，建议配置为false
	 */
	private Boolean poolPreparedStatements;

	/**
	 * 要启用PSCache，必须配置大于0，当大于0时，poolPreparedStatements自动触发修改为true。在Druid中，不会存在Oracle下PSCache占用内存过多的问题，可以把这个数值配置大一些，比如说100
	 */
	private Integer maxPoolPreparedStatementPerConnectionSize;

	/**
	 * 是否打开 removeAbandoned 功能
	 */
	private Boolean removeAbandoned;

	/**
	 * Abandoned 连接超时间, 单位: 秒
	 */
	private Integer removeAbandonedTimeoutMillis;

	/**
	 * 是否关闭 abanded 连接时输出错误日志
	 */
	private Boolean logAbandoned;

	/**
	 * 用来检测连接是否有效的sql，要求是一个查询语句，常用select 'x'。如果validationQuery为null，testOnBorrow、testOnReturn、testWhileIdle都不会其作用。
	 */
	private String validationQuery;

	/**
	 * 单位：秒，检测连接是否有效的超时时间。底层调用jdbc Statement对象的void setQueryTimeout(Integer seconds)方法
	 */
	private Integer validationQueryTimeout;

	/**
	 * 申请连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能。
	 */
	private Boolean testOnBorrow;

	/**
	 * 归还连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能。
	 */
	private Boolean testOnReturn;

	/**
	 * 建议配置为true，不影响性能，并且保证安全性。申请连接的时候检测，如果空闲时间大于timeBetweenEvictionRunsMillis，执行validationQuery检测连接是否有效。
	 */
	private Boolean testWhileIdle;

	/**
	 * @return the {@link #url}
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url
	 *            the {@link #url} to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the {@link #username}
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the {@link #username} to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the {@link #password}
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the {@link #password} to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the {@link #driverClassName}
	 */
	public String getDriverClassName() {
		return driverClassName;
	}

	/**
	 * @param driverClassName
	 *            the {@link #driverClassName} to set
	 */
	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}

	/**
	 * @return the {@link #filters}
	 */
	public String getFilters() {
		return filters;
	}

	/**
	 * @param filters
	 *            the {@link #filters} to set
	 */
	public void setFilters(String filters) {
		this.filters = filters;
	}

	/**
	 * @return the {@link #connectionProperties}
	 */
	public String getConnectionProperties() {
		return connectionProperties;
	}

	/**
	 * @param connectionProperties
	 *            the {@link #connectionProperties} to set
	 */
	public void setConnectionProperties(String connectionProperties) {
		this.connectionProperties = connectionProperties;
	}

	/**
	 * @return the {@link #initialSize}
	 */
	public Integer getInitialSize() {
		return initialSize;
	}

	/**
	 * @param initialSize
	 *            the {@link #initialSize} to set
	 */
	public void setInitialSize(Integer initialSize) {
		this.initialSize = initialSize;
	}

	/**
	 * @return the {@link #maxActive}
	 */
	public Integer getMaxActive() {
		return maxActive;
	}

	/**
	 * @param maxActive
	 *            the {@link #maxActive} to set
	 */
	public void setMaxActive(Integer maxActive) {
		this.maxActive = maxActive;
	}

	/**
	 * @return the {@link #minIdle}
	 */
	public Integer getMinIdle() {
		return minIdle;
	}

	/**
	 * @param minIdle
	 *            the {@link #minIdle} to set
	 */
	public void setMinIdle(Integer minIdle) {
		this.minIdle = minIdle;
	}

	/**
	 * @return the {@link #maxWait}
	 */
	public Long getMaxWait() {
		return maxWait;
	}

	/**
	 * @param maxWait
	 *            the {@link #maxWait} to set
	 */
	public void setMaxWait(Long maxWait) {
		this.maxWait = maxWait;
	}

	/**
	 * @return the {@link #timeBetweenEvictionRunsMillis}
	 */
	public Long getTimeBetweenEvictionRunsMillis() {
		return null != timeBetweenEvictionRunsMillis ? timeBetweenEvictionRunsMillis : DruidAbstractDataSource.DEFAULT_TIME_BETWEEN_EVICTION_RUNS_MILLIS;
	}

	/**
	 * @param timeBetweenEvictionRunsMillis
	 *            the {@link #timeBetweenEvictionRunsMillis} to set
	 */
	public void setTimeBetweenEvictionRunsMillis(Long timeBetweenEvictionRunsMillis) {
		this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
	}

	/**
	 * @return the {@link #minEvictableIdleTimeMillis}
	 */
	public Long getMinEvictableIdleTimeMillis() {
		return null != minEvictableIdleTimeMillis ? minEvictableIdleTimeMillis : 30 * 1000;
	}

	/**
	 * @param minEvictableIdleTimeMillis
	 *            the {@link #minEvictableIdleTimeMillis} to set
	 */
	public void setMinEvictableIdleTimeMillis(Long minEvictableIdleTimeMillis) {
		this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
	}

	/**
	 * @return the {@link #poolPreparedStatements}
	 */
	public Boolean getPoolPreparedStatements() {
		return poolPreparedStatements;
	}

	/**
	 * @param poolPreparedStatements
	 *            the {@link #poolPreparedStatements} to set
	 */
	public void setPoolPreparedStatements(Boolean poolPreparedStatements) {
		this.poolPreparedStatements = poolPreparedStatements;
	}

	/**
	 * @return the {@link #maxPoolPreparedStatementPerConnectionSize}
	 */
	public Integer getMaxPoolPreparedStatementPerConnectionSize() {
		return maxPoolPreparedStatementPerConnectionSize;
	}

	/**
	 * @param maxPoolPreparedStatementPerConnectionSize
	 *            the {@link #maxPoolPreparedStatementPerConnectionSize} to set
	 */
	public void setMaxPoolPreparedStatementPerConnectionSize(Integer maxPoolPreparedStatementPerConnectionSize) {
		this.maxPoolPreparedStatementPerConnectionSize = maxPoolPreparedStatementPerConnectionSize;
	}

	/**
	 * @return the {@link #removeAbandoned}
	 */
	public Boolean getRemoveAbandoned() {
		return null != removeAbandoned ? removeAbandoned : false;
	}

	/**
	 * @param removeAbandoned
	 *            the {@link #removeAbandoned} to set
	 */
	public void setRemoveAbandoned(Boolean removeAbandoned) {
		this.removeAbandoned = removeAbandoned;
	}

	/**
	 * @return the {@link #removeAbandonedTimeoutMillis}
	 */
	public Integer getRemoveAbandonedTimeoutMillis() {
		return null != removeAbandonedTimeoutMillis ? removeAbandonedTimeoutMillis : 300 * 1000;
	}

	/**
	 * @param removeAbandonedTimeoutMillis
	 *            the {@link #removeAbandonedTimeoutMillis} to set
	 */
	public void setRemoveAbandonedTimeoutMillis(Integer removeAbandonedTimeoutMillis) {
		this.removeAbandonedTimeoutMillis = removeAbandonedTimeoutMillis;
	}

	/**
	 * @return the {@link #logAbandoned}
	 */
	public Boolean getLogAbandoned() {
		return null != logAbandoned ? logAbandoned : false;
	}

	/**
	 * @param logAbandoned
	 *            the {@link #logAbandoned} to set
	 */
	public void setLogAbandoned(Boolean logAbandoned) {
		this.logAbandoned = logAbandoned;
	}

	/**
	 * @return the {@link #validationQuery}
	 */
	public String getValidationQuery() {
		return validationQuery;
	}

	/**
	 * @param validationQuery
	 *            the {@link #validationQuery} to set
	 */
	public void setValidationQuery(String validationQuery) {
		this.validationQuery = validationQuery;
	}

	/**
	 * @return the {@link #validationQueryTimeout}
	 */
	public Integer getValidationQueryTimeout() {
		return null != validationQueryTimeout ? validationQueryTimeout : -1;
	}

	/**
	 * @param validationQueryTimeout
	 *            the {@link #validationQueryTimeout} to set
	 */
	public void setValidationQueryTimeout(Integer validationQueryTimeout) {
		this.validationQueryTimeout = validationQueryTimeout;
	}

	/**
	 * @return the {@link #testOnBorrow}
	 */
	public Boolean getTestOnBorrow() {
		return null != testOnBorrow ? testOnBorrow : DruidAbstractDataSource.DEFAULT_TEST_ON_BORROW;
	}

	/**
	 * @param testOnBorrow
	 *            the {@link #testOnBorrow} to set
	 */
	public void setTestOnBorrow(Boolean testOnBorrow) {
		this.testOnBorrow = testOnBorrow;
	}

	/**
	 * @return the {@link #testOnReturn}
	 */
	public Boolean getTestOnReturn() {
		return null != testOnReturn ? testOnReturn : DruidAbstractDataSource.DEFAULT_TEST_ON_RETURN;
	}

	/**
	 * @param testOnReturn
	 *            the {@link #testOnReturn} to set
	 */
	public void setTestOnReturn(Boolean testOnReturn) {
		this.testOnReturn = testOnReturn;
	}

	/**
	 * @return the {@link #testWhileIdle}
	 */
	public Boolean getTestWhileIdle() {
		return null != testWhileIdle ? testWhileIdle : DruidAbstractDataSource.DEFAULT_WHILE_IDLE;
	}

	/**
	 * @param testWhileIdle
	 *            the {@link #testWhileIdle} to set
	 */
	public void setTestWhileIdle(Boolean testWhileIdle) {
		this.testWhileIdle = testWhileIdle;
	}

}
