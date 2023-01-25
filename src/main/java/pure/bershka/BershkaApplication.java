package pure.bershka;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.File;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableScheduling
public class BershkaApplication {

	public static void main(String[] args) {
		File file = new File("my-app-log");
		file.delete();
		SpringApplication.run(BershkaApplication.class, args);
	}

	@Configuration
	public class SpringFoxConfig {
	    @Bean
	    public Docket api() {
	        return new Docket(DocumentationType.SWAGGER_2)
	          .select()
	          .apis(RequestHandlerSelectors.any())
	          .paths(PathSelectors.any())
	          .build();
	    }
	}
}

