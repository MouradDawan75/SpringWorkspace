package fr.dawan.demospringcore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

@SpringBootApplication
//@ComponentScan(basePackages = "") - permet d'inclure' les packages externes au package de base dans le scan
public class DemospringcoreApplication {

	static ConfigurableApplicationContext context;

	public static void main(String[] args) {
		context = SpringApplication.run(DemospringcoreApplication.class, args);
		/*
		En Java classique:
		Création des objets
		Gestion des dépendances
		 */
		/*
		Moteur moteur = new Moteur();
		Voiture voiture = new Voiture(moteur);
		voiture.setMoteur(moteur);

		 */

		test();
		allBeans();
	}
	/*
	Bean: terme qui désigne un obejt crée par Spring
	 */
	static void test(){
		Voiture v = context.getBean(Voiture.class);
		v.Demarrer();
	}

	static void allBeans(){
		String[] beans = context.getBeanDefinitionNames();
		for(String bean : beans){
			System.out.println(bean);
		}
	}


}
