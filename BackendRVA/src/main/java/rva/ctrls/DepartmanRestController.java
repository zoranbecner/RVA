package rva.ctrls;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import rva.jpa.Departman;
import rva.repository.DepartmanRepository;

@Api(tags = { "departman CRUD operacije" })
@RestController
@CrossOrigin
public class DepartmanRestController {

	@Autowired
	private DepartmanRepository departmanRepository;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@ApiOperation(value = "Vraća kolekciju svih departmanaa iz baze podataka")
	@GetMapping("departman")
	public Collection<Departman> getDepartmani(){
		return departmanRepository.findAll();
	}

	@ApiOperation(value = "Vraća departman iz baze podataka čiji je id vrednost prosleđena kao path varijabla")
	@GetMapping("departman/{id}")
	public Departman getDepartman(@PathVariable ("id") Integer id){
		return departmanRepository.getOne(id);
	}


	@ApiOperation(value = "Briše departman iz baze podataka čiji je id vrednost prosleđena kao path varijabla")
	@Transactional
	@DeleteMapping("departman/{id}")
	public ResponseEntity<Departman> deleteDepartman(@PathVariable ("id") Integer id){
		if(!departmanRepository.existsById(id))
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		jdbcTemplate.execute("delete from student where departman = "+id);
		if (id != -100)
			departmanRepository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@ApiOperation(value = "Upisuje departman u bazu podataka")
	@PostMapping("departman")
	public ResponseEntity<Departman> insertDepartman(@RequestBody Departman departman){
		if(!departmanRepository.existsById(departman.getId())){
			departmanRepository.save(departman);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}

	@ApiOperation(value = "Modifikuje postojeću porudžbinu u bazi podataka")
	@PutMapping("departman")
	public ResponseEntity<Departman> updateDepartman(@RequestBody Departman departman){
		if(!departmanRepository.existsById(departman.getId()))
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		departmanRepository.save(departman);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
