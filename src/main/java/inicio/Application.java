package inicio;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
// Se le indican los paquetes que tiene que escanear para instanciar ( Spring) 
// los @Service, etc.
@ComponentScan(basePackages =  {"controller","service"})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public RestTemplate template() {
		return new RestTemplate();
	}
}
