package com.inqoo.travelofficeweb;

import com.inqoo.travelofficeweb.model.helper.AuditorAwareProvider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.envers.repository.support.EnversRevisionRepositoryFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAwareProvider")
@EnableJpaRepositories(repositoryFactoryBeanClass = EnversRevisionRepositoryFactoryBean.class)
public class TravelOfficeWebApplication {
    @Bean
    public AuditorAware<String> auditorAwareProvider() {
        return new AuditorAwareProvider();
    }

    public static void main(String[] args) {
        SpringApplication.run(TravelOfficeWebApplication.class, args);
    }
}
