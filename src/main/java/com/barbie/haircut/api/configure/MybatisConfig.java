package com.barbie.haircut.api.configure;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

//@Configuration
//@MapperScan(basePackages = "com.barbie.haircut.api.**.impl", sqlSessionFactoryRef = "SqlSessionFactory", sqlSessionTemplateRef = "SessionTemplate")
//@Slf4j
//public class MybatisConfig {
    //@Value("${mybatis.mapper-locations:defaultValue}")
    //String mapperLocation = "classpath:/mapper/**/*.xml";

    //@Value("${mybatis.config-location:defaultValue}")
    //String mapperConfig = "classpath:/config/mapper-config.xml";

    /*@Primary
    @Bean(name = "dataSource")
    @ConfigurationProperties(prefix = "spring.datasource.hikari.main")
    public DataSource DataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }*/

    /*@Primary
    @Bean(name = "SqlSessionFactory")
    public SqlSessionFactory SqlSessionFactory(@Qualifier("dataSource") DataSource DataSource, ApplicationContext applicationContext) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        log.debug("mapperLocation=>"+mapperLocation);
        log.debug("mapperConfig=>"+mapperConfig);
        sqlSessionFactoryBean.setDataSource(DataSource);
        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources(mapperLocation));
        sqlSessionFactoryBean.setConfigLocation(applicationContext.getResource(mapperConfig));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "SessionTemplate")
    public SqlSessionTemplate SessionTemplate(@Qualifier("SqlSessionFactory") SqlSessionFactory firstSqlSessionFactory) {
        return new SqlSessionTemplate(firstSqlSessionFactory);
    }
}*/
