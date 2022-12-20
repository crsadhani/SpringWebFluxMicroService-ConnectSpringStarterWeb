package ams.cs.ms.siemens.com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@EntityScan("ams.cs.ms.siemens.com.entity")
@EnableNeo4jRepositories("ams.cs.ms.siemens.com.repository")
@EnableWebFlux
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
