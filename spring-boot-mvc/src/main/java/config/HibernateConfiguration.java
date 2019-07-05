package config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
@EntityScan(basePackages = { "com.locationguru.automation.model" })
public class HibernateConfiguration
{
	private static final Logger logger = LogManager.getLogger(HibernateConfiguration.class);

}
