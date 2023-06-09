package rva.ctrls;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import rva.jpa.Status;
import rva.repository.StatusRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/*
 * @RestController - predstavlja anotaciju koja se koristi na nivou klase.
 * @RestController anotacija je kombinacija anotacija @Controller i @ResponseBody
 * @Controller - anotacija koja se koristi da bi se označilo da se radi o klasi koja je
 * Spring Controller i može se koristiti u npr. Spring MVC
 * @ResponseBody - označava da rezultat izvršavanja treba da bude smešten u Response Body u
 * željenom formatu (podrazumevani format je JSON). Više informacija HTTP porukama možete
 * pronaći na linku
 * https://developer.mozilla.org/en-US/docs/Web/HTTP/Messages
 */
@Api(tags = { "Status CRUD operacije" })
@RestController
@CrossOrigin
public class StatusRestController {

	/*
	 * Anotacija @Autowired se može primeniti nad varijablama instace, setter
	 * metodama i konstruktorima. Označava da je neophodno injektovati zavisni
	 * objekat. Prilikom pokretanja aplikacije IoC kontejner prolazi kroz kompletan
	 * kod tražeči anotacije koje označavaju da je potrebno kreirati objekte.
	 * Upotrebom @Autowired anotacije stavljeno je do znanja da je potrebno kreirati
	 * objekta klase koja će implementirati repozitorijum AriklRepository i
	 * proslediti klasi ArtiklRestController referencu na taj objekat.
	 */
	@Autowired
	private StatusRepository statusRepository;
	/*
	 * JdbcTemplate klasa pojednostavljuje korišćenje JDBC (Java Database
	 * Connectivity), objekat ove klase će se koristiti u metodi delete() i zato je
	 * deklarisan i anotiran sa @Autowired
	 */
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@ApiOperation(value = "Vraća kolekciju svih statusa iz baze podataka")
	@GetMapping("status")
	public Collection<Status> getStatusi() {
		return statusRepository.findAll();
	}

	/*
	 * U slučaju metode getOne(), novina je uvođenje promenljive koja je
	 * predstavljena kao {id} u @GetMapping("artikl/{id}"). Mapiranje promenljive u
	 * vrednost koja se prosleđuje konkretnoj metodi getOne() vrši se upotrebom
	 * anotacije @PathVariable. U konkretnom slučaju HTTP GET zahtev upućen na
	 * adresu localhost:8083/artikl/1 biće prosleđen ovoj metodi, a vrednost 1 kao
	 * ID.
	 *
	 * Poziv metode statusRepository.getOne(id) će vratiti konkretan artikal sa
	 * datim ID-je i taj artikal će potom biti prikazan u browseru u JSON formatu.
	 */
	@ApiOperation(value = "Vraća status iz baze podataka čiji je id vrednost prosleđena kao path varijabla")
	@GetMapping("status/{id}")
	public Status getStatus(@PathVariable("id") Integer id) {
		return statusRepository.getOne(id);
	}

	@ApiOperation(value = "Vraća kolekciju svih statusa iz baze podataka koji u nazivu sadrže string prosleđen kao path varijabla")
	@GetMapping("statusNaziv/{naziv}")
	public Collection<Status> getStatusByNaziv(@PathVariable("naziv") String naziv) {
		return statusRepository.findByNazivContainingIgnoreCase(naziv);
	}

	/*
	 * HTTP DELETE je jedna od HTTP metoda koja je analogna opciji DELETE iz CRUD
	 * operacija. Anotacija @DELETEMapping se koristi kako bi se mapirao HTTP DELETE
	 * zahtev. Predstavlja skraćenu verziju metode @RequestMapping(method =
	 * RequestMethod.DELETE) U konkretnom slučaju HTTP PUT zahtevi upućeni na adresu
	 * localhost:8083/artikl/{id} biće prosleđeni ovoj metodi. Poziv metode
	 * statusRepository.deleteByID(id) će obrisati artikl sa prosleđenim ID-ije iz
	 * baze podataka.
	 */
	@ApiOperation(value = "Briše status iz baze podataka čiji je id vrednost prosleđena kao path varijabla")
	@DeleteMapping("status/{id}")
	@Transactional
	public ResponseEntity<Status> deleteStatus(@PathVariable("id") Integer id) {
		if (!statusRepository.existsById(id))
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		jdbcTemplate.execute("delete from student where status = " + id);
		statusRepository.deleteById(id);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	/*
	 * HTTP POST je jedna od HTTP metoda koja je analogna opciji CREATE iz CRUD
	 * operacija. Anotacija @PostMapping se koristi kako bi se mapirao HTTP POST
	 * zahtev. Predstavlja skraćenu verziju metode @RequestMapping(method =
	 * RequestMethod.POST) U konkretnom slučaju HTTP POST zahtevi upućeni na adresu
	 * localhost:8083/artikl biće prosleđeni ovoj metodi. Poziv metode
	 * statusRepository.save(artikl) će sačuvati prosleđeni artikl u bazi podataka
	 */
	@ApiOperation(value = "Upisuje status u bazu podataka")
	@PostMapping("status")
	public ResponseEntity<Status> insertStatus(@RequestBody Status status) {
		if (!statusRepository.existsById(status.getId())) {
			statusRepository.save(status);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}

	/*
	 * HTTP PUT je jedna od HTTP metoda koja je analogna opciji UPDATE iz CRUD
	 * operacija. Anotacija @PutMapping se koristi kako bi se mapirao HTTP PUT
	 * zahtev. Predstavlja skraćenu verziju metode @RequestMapping(method =
	 * RequestMethod.PUT) U konkretnom slučaju HTTP PUT zahtevi upućeni na adresu
	 * localhost:8083/artikl/{id} biće prosleđeni ovoj metodi. Poziv metode
	 * statusRepository.save(artikl) će izmeniti artikl sa prosleđenim ID-ijem i
	 * prosleđenim sadržajem u bazi podataka.
	 */
	@ApiOperation(value = "Modifikuje postojeći status u bazi podataka")
	@PutMapping("status")
	public ResponseEntity<Status> updateStatus(@RequestBody Status status) {
		if (!statusRepository.existsById(status.getId()))
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		statusRepository.save(status);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
