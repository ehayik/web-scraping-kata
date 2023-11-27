package org.github.ehayik.kata.webscrapping.infrastructure.driverpool;

import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.openqa.selenium.WebDriver;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.context.annotation.Profile;

import static org.springframework.jmx.support.RegistrationPolicy.IGNORE_EXISTING;

@Configuration
@EnableMBeanExport(registration = IGNORE_EXISTING)
@EnableConfigurationProperties(WebDriverPoolProperties.class)
class WebDriverPoolConfig {

    @Bean
    PoolingWebDriverManager poolingWebDriverManager(GenericObjectPool<WebDriver> webDriverPool) {
        return new PoolingWebDriverManager(webDriverPool);
    }

    @Bean
    GenericObjectPool<WebDriver> webDriverPool(WebDriverPoolProperties properties, PooledObjectFactory<WebDriver> driverFactory) {
        var poolConfig = new GenericObjectPoolConfig<WebDriver>();
        poolConfig.setMaxTotal(properties.getMaxTotal());
        poolConfig.setMinIdle(properties.getMinIdle());
        poolConfig.setMaxIdle(properties.getMaxIdle());

        return new GenericObjectPool<>(driverFactory, poolConfig);
    }

    @Configuration
    @Profile("standalone")
    static class PooledChromeDriverConfig {

        @Bean
        PooledObjectFactory<WebDriver> webDriverFactory() {
            return new PooledWebDriverFactory(new ChromeWebDriverFactory());
        }
    }

    @Configuration
    @Profile("container")
    static class PooledFirefoxDriverConfig {

        @Bean
        PooledObjectFactory<WebDriver> webDriverFactory() {
            return new PooledWebDriverFactory(new FirefoxWebDriverFactory());
        }
    }
}

