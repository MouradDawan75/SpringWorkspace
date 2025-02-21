package fr.dawan.demospringmvc;

import fr.dawan.demospringmvc.interceptors.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class DemospringmvcApplication {

	@Autowired
	private LoginInterceptor loginInterceptor;

	@Bean
	public PasswordEncoder getENcoder(){
		return new BCryptPasswordEncoder();
	}

	public static void main(String[] args) {
		SpringApplication.run(DemospringmvcApplication.class, args);
	}

	//Mise à de la configuration MVC, en ajoutant le LongInterceptor

	@Bean
	public WebMvcConfigurer getConfiguration(){

		return new WebMvcConfigurer() {
			@Override
			public void addInterceptors(InterceptorRegistry registry) {
				registry.addInterceptor(loginInterceptor);
			}

		};

	}

}
