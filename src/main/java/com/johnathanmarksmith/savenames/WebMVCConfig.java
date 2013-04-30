package com.johnathanmarksmith.savenames;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.TransactionException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.mvc.annotation.DefaultAnnotationHandlerMapping;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import java.util.Locale;
import java.util.Properties;


@Configuration
@EnableWebMvc
@PropertySource("classpath:application.properties")
@Import(DatabaseConfiguration.class)
public class WebMVCConfig extends WebMvcConfigurerAdapter
{

    private static final String MESSAGE_SOURCE = "/WEB-INF/classes/messages";
    private static final Logger logger = LoggerFactory.getLogger(WebMVCConfig.class);

    @Autowired
    Environment env;

    @Bean
    public ViewResolver resolver()
    {
        UrlBasedViewResolver url = new UrlBasedViewResolver();
        url.setPrefix("/WEB-INF/view/");
        url.setViewClass(JstlView.class);
        url.setSuffix(".jsp");
        return url;
    }

    @Bean(name = "messageSource")
    public MessageSource configureMessageSource()
    {
        logger.debug("setting up message source");
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename(MESSAGE_SOURCE);
        messageSource.setCacheSeconds(5);
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    public LocaleResolver localeResolver()
    {
        SessionLocaleResolver lr = new SessionLocaleResolver();
        lr.setDefaultLocale(Locale.ENGLISH);
        return lr;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry)
    {
        logger.debug("setting up resource handlers");
        registry.addResourceHandler("/resources/").addResourceLocations("/resources/**");
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer)
    {
        logger.debug("configureDefaultServletHandling");
        configurer.enable();
    }

    @Override
    public void addInterceptors(final InterceptorRegistry registry)
    {
        registry.addInterceptor(new LocaleChangeInterceptor());
    }


    public
    @Bean
    HandlerExceptionResolver exceptionResolver()
    {
        Properties mappings = new Properties();
        mappings.put("org.springframework.web.servlet.PageNotFound", "404");
        mappings.put(DataAccessException.class.getName(), "dataAccessFailure");
        mappings.put(TransactionException.class.getName(), "dataAccessFailure");

        SimpleMappingExceptionResolver resolver = new SimpleMappingExceptionResolver();
        resolver.setExceptionMappings(mappings);

        return resolver;
    }


    @Bean
    public DefaultAnnotationHandlerMapping mapping()
    {
        DefaultAnnotationHandlerMapping m = new DefaultAnnotationHandlerMapping();
        m.setDetectHandlersInAncestorContexts(true);
        return m;
    }
}
