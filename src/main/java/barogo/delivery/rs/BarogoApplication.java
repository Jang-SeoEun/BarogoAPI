package barogo.delivery.rs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.PageableHandlerMethodArgumentResolverCustomizer;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
public class BarogoApplication {

	public static void main(String[] args) {
		SpringApplication.run(BarogoApplication.class, args);
	}

	public PageableHandlerMethodArgumentResolverCustomizer customize() {
		return p->{
			p.setOneIndexedParameters(true);
			p.setMaxPageSize(10);
		};
	}

}
