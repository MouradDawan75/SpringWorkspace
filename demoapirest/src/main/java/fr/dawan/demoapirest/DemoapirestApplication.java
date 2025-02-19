package fr.dawan.demoapirest;

import fr.dawan.demoapirest.entities.Product;
import fr.dawan.demoapirest.entities.Supplier;
import fr.dawan.demoapirest.tools.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class DemoapirestApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoapirestApplication.class, args);

	}

	@Bean
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(">>>>>> test ModelMapper<<<<<<<<<<<<");

		Contact c = new Contact();
		c.setFirstName("DUPONT");
		c.setLastName("Jean");
		ContactDto dto = ModelMapperDemo.dtoFormEntity(c);

		System.out.println(dto);

		Supplier s = new Supplier(); //id = 0, version = 0, name = null, products = null
		s.getProducts().add(new Product());

		IRepo repo = new FichierRepo();
		repo.save();
		repo.delete();

		/*
		Polymorphisme: un objet peut prendre plusieurs formes
		c'est le fait que l'objet puisse prendre la forme de tous les objets enfants. C'est une conséquence de l'héritage
		 */

		TestPolymorphisme(new DataBaseRepo());
		TestPolymorphisme(new FichierRepo());
		TestPolymorphisme(new OracleRepo());

	}

	static void TestPolymorphisme(IRepo repo){
		repo.save();
		repo.delete();
	}
}
