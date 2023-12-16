package org.github.ehayik.kata.webscraping.infrastructure.webdriver.pool;

import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.github.ehayik.kata.webscraping.infrastructure.webdriver.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;

import java.time.Duration;

import static org.springframework.jmx.support.RegistrationPolicy.IGNORE_EXISTING;

@Configuration
@EnableMBeanExport(registration = IGNORE_EXISTING)
class WebDriverPoolConfig {

    @Bean
    PoolingWebDriverManager poolingWebDriverManager(@Value("${web-driver.timeout: 1s}") Duration timeout, GenericObjectPool<WebDriver> webDriverPool) {
        return new PoolingWebDriverManager(timeout, webDriverPool);
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
    PooledObjectFactory<WebDriver> pooledObjectFactory(WebDriverFactory webDriverFactory) {
        return new PooledWebDriverFactory(webDriverFactory);
    }
}
