package com.muscidae.parrot.common.basic.spring;

import lombok.Getter;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.boot.context.properties.bind.BindHandler;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.boot.context.properties.bind.PropertySourcesPlaceholdersResolver;
import org.springframework.boot.context.properties.bind.handler.IgnoreTopLevelConverterNotFoundBindHandler;
import org.springframework.boot.context.properties.source.ConfigurationPropertySource;
import org.springframework.boot.context.properties.source.ConfigurationPropertySources;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.env.PropertySources;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

/**
 * @author muscidae
 * @date 2019/3/31 10:49
 * @copyright ©2019
 * @description Bean动态替换类
 */
@Component
@Deprecated
public class BeanRefresh implements ApplicationContextAware {

    @Getter
    private ConfigurableApplicationContext applicationContext;

    private PropertySources propertySources;
    private Binder binder;

    /**
     * @author muscidae
     * @date 2019/3/30 10:46
     * @description 根据属性前缀动态替换 Bean
     * @param configPrefix
     * @param bean
     * @return void
     */
    public final void bind(String configPrefix, Bindable<?> bean) {
        BindHandler handler = new IgnoreTopLevelConverterNotFoundBindHandler();
        this.binder.bind(configPrefix, bean, handler);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = (ConfigurableApplicationContext) applicationContext;
        this.propertySources = this.applicationContext.getEnvironment().getPropertySources();
        this.binder = new Binder(getConfigurationPropertySources(), getPropertySourcesPlaceholdersResolver(),
                getConversionService(), getPropertyEditorInitializer());
    }

    private Iterable<ConfigurationPropertySource> getConfigurationPropertySources() {
        return ConfigurationPropertySources.from(this.propertySources);
    }

    private PropertySourcesPlaceholdersResolver getPropertySourcesPlaceholdersResolver() {
        return new PropertySourcesPlaceholdersResolver(this.propertySources);
    }

    private ConversionService getConversionService() {
        return this.applicationContext.getBeanFactory().getConversionService();
    }

    private Consumer<PropertyEditorRegistry> getPropertyEditorInitializer() {
        return this.applicationContext.getBeanFactory()::copyRegisteredEditorsTo;
    }

}
