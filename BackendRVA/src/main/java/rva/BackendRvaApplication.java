package rva;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;


/*
 * @SpringBootApplication - anotacija se postavlja na klasi koja će se koristiti
 * za pokretanje aplikacije. Klasa koja pokreće aplikaciju mora se nalaziti u
 * osnovnom (root-nom) paketu. Predstavlja kobinaciju anotacija @Configuration, @EnableAutoConfiguration i
 * @ComponentScan
 * @Configuration - anotacija koja označava klasu koja definiše Spring Bean-ove. 
 * @EnableAutoConfiguration - anotacija koja označava klasu koje će kreirati Spring Bean-ove i
 * inicijalizovati različita druga podešavanja
 * @ComponentScan - anotacija koja označava gde će se tražiti klase, metode ili varijable instanci
 * koje imaju anotacije
 * 
*/

@SpringBootApplication
public class BackendRvaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendRvaApplication.class, args);
	}
	/*
	 * @Bean - koristi se na nivou metode, kako bi se stavilo do znanja da je
	 * potrebno kreirati Spring Bean. Spring Bean predstavlja objekat kojim upravlja
	 * (kreira i inicijalizuje) Spring IoC kontejner.
	 */
	/*
	 * ApplicationContex - zadužen za učitavanje definicija Bean-ova, međusobno
	 * povezivanje Bean-ova i prosleđivanje Bean-ova kada su potrebni
	 */
	/*
	 * U narednoj metodi prikazuju se svi Bean-ovi koji se kreiraju prilikom
	 * pokretanja aplikacije, ne samo oni koje je kreirao korisnik npr.
	 * HelloRestController, ArtiklController, itd.
	 *
	 */

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext context){
		return args -> {
			System.out.println("Beans provided by Spring framework");
			String[] beanNames = context.getBeanDefinitionNames();
			for(String beanName: beanNames){
				System.out.println(beanName);
			}
		};
	}

}
