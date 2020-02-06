package com.thefirstwind.eshop.inventory;

import java.util.HashSet;
import java.util.Set;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

/**
 * Hello world!
 *
 */
@Slf4j
@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan
@MapperScan("com.thefirstwind.eshop.inventory.mapper")
public class EshopInventoryApplication {
	public static void main(String[] args) {
		log.info("start EshopInventoryApplication");
		SpringApplication.run(EshopInventoryApplication.class, args);
	}

	@Bean
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource dataSource() {
		return DruidDataSourceBuilder.create().build();
	}

	@Bean
	public SqlSessionFactory sqlSessionFactoryBean() throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource());
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:/mybatis/*.xml"));
		return sqlSessionFactoryBean.getObject();
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}

	/**
	 * redis cluster连接设置
	 * 
	 * @return
	 */
	@Bean
	public JedisCluster JedisClusterFactory() {
		Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
		jedisClusterNodes.add(new HostAndPort("192.168.31.101", 7001));
		jedisClusterNodes.add(new HostAndPort("192.168.31.101", 7011));
		jedisClusterNodes.add(new HostAndPort("192.168.31.101", 7021));
		jedisClusterNodes.add(new HostAndPort("192.168.31.102", 7002));
		jedisClusterNodes.add(new HostAndPort("192.168.31.102", 7012));
		jedisClusterNodes.add(new HostAndPort("192.168.31.102", 7022));
		JedisCluster jedisCluster = new JedisCluster(jedisClusterNodes);
		return jedisCluster;
	}
}
