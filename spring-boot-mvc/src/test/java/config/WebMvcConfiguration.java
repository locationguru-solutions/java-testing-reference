package config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebMvcConfiguration
{
	private static final Logger logger = LogManager.getLogger(WebMvcConfiguration.class);

	/*@Bean
	public TestRestTemplate restTemplate()
	{
		return new TestRestTemplate();
	}*/
}
