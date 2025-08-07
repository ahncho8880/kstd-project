package com.kstd.project.common.config;

import io.r2dbc.spi.ConnectionFactory;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing;
import org.springframework.r2dbc.connection.R2dbcTransactionManager;
import org.springframework.transaction.reactive.TransactionalOperator;

@Configuration
@EnableR2dbcAuditing(dateTimeProviderRef = "kstDateTimeProvider")
public class R2dbcConfig {

    @Bean
    public DateTimeProvider kstDateTimeProvider() {
        return () -> Optional.of(LocalDateTime.now(ZoneId.of("Asia/Seoul")));
    }

    @Bean
    public TransactionalOperator transactionalOperator(ConnectionFactory connectionFactory){
        return TransactionalOperator.create(new R2dbcTransactionManager(connectionFactory));
    }
}
