package com.harry.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @Description:
 * @author: wangruirui
 * @date: 2018/11/22
 */
@Configuration
@ConfigurationProperties(prefix = "jpush")
class JPushConfig {

    String sound;
    boolean apnsProduction;
    List<keySecret> keySecrets;

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public boolean isApnsProduction() {
        return apnsProduction;
    }

    public void setApnsProduction(boolean apnsProduction) {
        this.apnsProduction = apnsProduction;
    }

    public List<keySecret> getKeySecrets() {
        return keySecrets;
    }

    public void setKeySecrets(List<keySecret> keySecrets) {
        this.keySecrets = keySecrets;
    }
}



