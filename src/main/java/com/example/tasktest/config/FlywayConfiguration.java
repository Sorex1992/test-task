package com.example.tasktest.config;

import lombok.RequiredArgsConstructor;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

//@Configuration
//@RequiredArgsConstructor
//public class FlywayConfiguration {
//
//    private final ConnectionApplicationProperties connectionApplicationProperties;
//    private final MultiDatabaseConfig multiDatabaseConfig;
//
//    @Bean
//    public List<Flyway> dynamicConnectionFlyway() {
//        List<Flyway> flyways = new ArrayList<>();
//
//        for (int i = 0; i < connectionApplicationProperties.getConnectionConfigModels().size(); i++) {
//            DataSource dataSource = multiDatabaseConfig.createDataSource(connectionApplicationProperties.getConnectionConfigModels().get(i));
//            String migrationLocation = "classpath:db/migration/" + connectionApplicationProperties.getConnectionConfigModels().get(i).getName();
//            Flyway flyway = createFlyway(dataSource, migrationLocation);
//            flyway.migrate();
//            flyways.add(flyway);
//        }
//
//        return flyways;
//    }
//
//    public Flyway createFlyway(DataSource dataSource, String migrationLocation) {
//        return Flyway.configure()
//                .dataSource(dataSource)
//                .locations(migrationLocation)
//                .load();
//    }
//}
@Configuration
@RequiredArgsConstructor
public class FlywayConfiguration {

    private final ConnectionApplicationProperties connectionApplicationProperties;

    @Bean
    public List<Flyway> dynamicConnectionFlyway(@Qualifier("jdbcTemplates") List<JdbcTemplate> jdbcTemplates) {
        List<Flyway> flyways = new ArrayList<>();

        for (int i = 0; i < jdbcTemplates.size(); i++) {
            JdbcTemplate jdbcTemplate = jdbcTemplates.get(i);
            String migrationLocation = "classpath:db/migration/"
                    + connectionApplicationProperties.getConnectionConfigModels().get(i).getName();
            Flyway flyway = createFlyway(jdbcTemplate, migrationLocation);
            flyway.migrate();
            flyways.add(flyway);
        }
        return flyways;
    }

    private Flyway createFlyway(JdbcTemplate jdbcTemplate, String migrationLocation) {
        return Flyway.configure()
                .dataSource(jdbcTemplate.getDataSource())
                .locations(migrationLocation)
                .load();
    }
}
