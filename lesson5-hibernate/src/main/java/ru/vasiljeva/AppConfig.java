package ru.vasiljeva;

import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import ru.vasiljeva.dao.CustomerDao;
import ru.vasiljeva.dao.ItemDao;
import ru.vasiljeva.dao.ProductDao;
import ru.vasiljeva.dao.impl.CustomerDaoImpl;
import ru.vasiljeva.dao.impl.ItemDaoImpl;
import ru.vasiljeva.dao.impl.ProductDaoImpl;
import ru.vasiljeva.service.CartService;
import ru.vasiljeva.service.ProductService;
import ru.vasiljeva.service.impl.CartServiceImpl;
import ru.vasiljeva.service.impl.ProductServiceImpl;

@Configuration
@ComponentScan("ru.vasiljeva")
@EnableTransactionManagement
@PropertySource("classpath:db.properties")
public class AppConfig {

	private static final String PROPERTY_NAME_DATABASE_DRIVER = "db.driver";
	private static final String PROPERTY_NAME_DATABASE_PASSWORD = "db.password";
	private static final String PROPERTY_NAME_DATABASE_URL = "db.url";
	private static final String PROPERTY_NAME_DATABASE_USERNAME = "db.username";

	private static final String PROPERTY_NAME_HIBERNATE_DIALECT = "hibernate.dialect";
	private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
	private static final String PROPERTY_NAME_HIBERNATE_AUTOCOMMIT = "hibernate.connection.autocommit";
	private static final String PROPERTY_NAME_HIBERNATE_DDL_AUTO = "hibernate.hbm2ddl.auto";
	private static final String PROPERTY_NAME_HIBERNATE_CHECK_NULLABILITY = "hibernate.check_nullability";
	private static final String PROPERTY_NAME_HIBERNATE_FORMAT_SQL = "hibernate.format_sql";

	private static final String PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN = "entitymanager.packages.to.scan";

	@Resource
	private Environment env;

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();

		dataSource.setDriverClassName(env.getRequiredProperty(PROPERTY_NAME_DATABASE_DRIVER));
		dataSource.setUrl(env.getRequiredProperty(PROPERTY_NAME_DATABASE_URL));
		dataSource.setUsername(env.getRequiredProperty(PROPERTY_NAME_DATABASE_USERNAME));
		dataSource.setPassword(env.getRequiredProperty(PROPERTY_NAME_DATABASE_PASSWORD));

		return dataSource;
	}

	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
		sessionFactoryBean.setDataSource(dataSource());
		sessionFactoryBean.setPackagesToScan(env.getRequiredProperty(PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN));
		sessionFactoryBean.setHibernateProperties(hibProperties());
		return sessionFactoryBean;
	}

	private Properties hibProperties() {
		Properties properties = new Properties();
		properties.put(PROPERTY_NAME_HIBERNATE_DIALECT, env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_DIALECT));
		properties.put(PROPERTY_NAME_HIBERNATE_SHOW_SQL, env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_SHOW_SQL));
		properties.put(PROPERTY_NAME_HIBERNATE_AUTOCOMMIT, env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_AUTOCOMMIT));
		properties.put(PROPERTY_NAME_HIBERNATE_DDL_AUTO, env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_DDL_AUTO));
		properties.put(PROPERTY_NAME_HIBERNATE_CHECK_NULLABILITY,
				env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_CHECK_NULLABILITY));
		properties.put(PROPERTY_NAME_HIBERNATE_FORMAT_SQL, env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_FORMAT_SQL));

		return properties;
	}

	@Bean
	public HibernateTransactionManager transactionManager() {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory().getObject());
		return transactionManager;
	}

	/* DAOs */
	@Bean
	public ProductDao productDao() {
		ProductDaoImpl dao = new ProductDaoImpl();
		dao.setSessionFactory(sessionFactory().getObject());
		return dao;
	}

	@Bean
	public CustomerDao customerDao() {
		CustomerDaoImpl dao = new CustomerDaoImpl();
		dao.setSessionFactory(sessionFactory().getObject());
		return dao;
	}

	@Bean
	public ItemDao itemDao() {
		ItemDaoImpl dao = new ItemDaoImpl();
		dao.setSessionFactory(sessionFactory().getObject());
		return dao;
	}

	/* Services */   
	@Bean
	public ProductService productService() {
		ProductServiceImpl service = new ProductServiceImpl();
		service.setProductDao(productDao());
		return service;
	}
	
	@Bean
	public CartService cartService() {
		CartServiceImpl service = new CartServiceImpl();
		service.setCustomerDao(customerDao());
		service.setProductDao(productDao());
		service.setItemDao(itemDao());
		return service;
	}
}
