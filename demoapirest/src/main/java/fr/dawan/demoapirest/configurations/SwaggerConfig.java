package fr.dawan.demoapirest.configurations;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    /*
    Compléter la doc de l'api
     */

    @Bean
    public OpenAPI getDoc(){

        Server devServer = new Server();
        devServer.setUrl("http://localhost:8085");
        devServer.setDescription("Server URL in Dev. Environment");

        Server prodServer = new Server();
        prodServer.setUrl("http://localhost:8082");
        prodServer.setDescription("Server URL in Prod. Environment");

        Contact contact = new Contact();
        contact.setEmail("contact@dawan.fr");
        contact.setName("Admin");
        contact.setUrl("http://www.dawan.fr/contact");

        License mitLicence = new License().name("Dawan Licence").url("http://www.dawan.fr/licences/mit");

        Info info = new Info()
                .title("Tutorial Spring Boot Rest API")
                .version("1.0")
                .contact(contact)
                .description("This API expose endpoints to tutorials....")
                .termsOfService("http://www.dawan.fr/terms").license(mitLicence);

        return new OpenAPI().info(info).servers(List.of(devServer, prodServer));

    }
}
