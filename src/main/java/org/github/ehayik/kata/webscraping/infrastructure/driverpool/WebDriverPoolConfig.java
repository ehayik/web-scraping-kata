package org.github.ehayik.kata.webscraping.infrastructure.driverpool;

import static org.springframework.jmx.support.RegistrationPolicy.IGNORE_EXISTING;

import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.openqa.selenium.WebDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;

@Configuration
@EnableMBeanExport(registration = IGNORE_EXISTING)
class WebDriverPoolConfig {

    @Bean
    PoolingWebDriverManager poolingWebDriverManager(GenericObjectPool<WebDriver> webDriverPool) {
        return new PoolingWebDriverManager(webDriverPool);
    }

    @Bean
    GenericObjectPool<WebDriver> webDriverPool(
            WebDriverPoolProperties properties, PooledObjectFactory<WebDriver> driverFactory) {
        var poolConfig = new GenericObjectPoolConfig<WebDriver>();
        poolConfig.setMaxTotal(properties.getMaxTotal());
        poolConfig.setMinIdle(properties.getMinIdle());
        poolConfig.setMaxIdle(properties.getMaxIdle());
        return new GenericObjectPool<>(driverFactory, poolConfig);
    }

    @Bean
    PooledObjectFactory<WebDriver> pooledObjectFactory(
            WebDriverProperties properties, WebDriverFactory webDriverFactory) {
        return new PooledWebDriverFactory(webDriverFactory, properties);
    }

    @Bean
    WebDriverFactory webDriverFactory(WebDriverProperties properties) {
        return switch (properties.getBrowser()) {
            case CHROME -> new ChromeWebDriverFactory();
            case FIREFOX -> new FirefoxWebDriverFactory();
        };
    }
}
