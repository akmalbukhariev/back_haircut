package com.barbie.haircut.api.configure;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
//@ComponentScan(basePackages = "com.barbie.haircut.api")
public class JasyptConfig {

    @Value("${jasypt.encryptor.password:defaultValue}")
    private String jasyptPwd;

    @Bean(name = "jasyptStringEncryptor")
    public StringEncryptor stringEncryptor() {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword(jasyptPwd);              // Key used for encryption
        config.setAlgorithm("PBEWithMD5AndDES");    // encryption algorithm
        config.setKeyObtentionIterations("1000");   // Number of hashes to repeat
        config.setPoolSize("1");                    // instance pool
        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator"); // salt generated class
        config.setStringOutputType("base64");       // Encoding method
        encryptor.setConfig(config);
        return encryptor;
    }
}
