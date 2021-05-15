package com.scau.ruan.hotel.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Create By  @林俊杰
 * 2021/5/15 20:09
 *
 * @version 1.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "mail")
public class EmailConfig {

    @Value("${mail.mailFrom}")
    private String mailMailFrom;

    @Value("${mail.domainName}")
    private String mailDomainName;
}
