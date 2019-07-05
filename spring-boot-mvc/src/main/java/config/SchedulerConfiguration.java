package config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Configures infrastructure required for asynchronous task execution and scheduling.
 */
@Configuration
@EnableScheduling
@EnableAsync(proxyTargetClass = true)
public class SchedulerConfiguration
{
	private static final Logger logger = LogManager.getLogger(SchedulerConfiguration.class);


}
