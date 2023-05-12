package rva.ctrls;

import java.math.BigDecimal;
import java.util.Collection;

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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import rva.jpa.Departman;
import rva.jpa.Student;
import rva.repository.DepartmanRepository;
import rva.repository.StudentRepository;

@Api(tags = { "Student CRUD operacije" })
@RestController
@CrossOrigin
public class StudentRestController {

	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private DepartmanRepository departmanRepository;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@ApiOperation(value = "Vraća kolekciju svih stavki studenata iz baze podataka")
	@GetMapping(value = "student")
	public Collection<Student> getStudenti(){
		return studentRepository.findAll();
	}

	@ApiOperation(value = "Vraća studente iz baze podataka čiji je id vrednost prosleđena kao path varijabla")
	@GetMapping(value = "student/{id}")
	public ResponseEntity<Student> getStudent(@PathVariable("id") Integer id){
		Student student = studentRepository.getOne(id);
		return new ResponseEntity<Student>(student, HttpStatus.OK);
	}

	@ApiOperation(value = "Vraća kolekciju studenata iz baze podataka čiji je id departmana vrednost prosleđena kao path varijabla")
	@GetMapping(value = "studentiZaDepartmaneId/{id}")
	public Collection<Student> studentPoDepartmanuId(@PathVariable("id") int id){
		Departman d = departmanRepository.getOne(id);
		return studentRepository.findByDepartman(d);
	}

	@ApiOperation(value = "Vraća kolekciju svih studenata iz baze podataka koji u imenu sadrže string prosleđen kao path varijabla")
	@GetMapping("studentIme/{ime}")
	public Collection<Student> getStudentByIme(@PathVariable("ime") String ime) {
		return studentRepository.findByImeContainingIgnoreCase(ime);
	}

	@ApiOperation(value = "Briše studenta iz baze podataka čiji je id vrednost prosleđena kao path varijabla")
	@DeleteMapping (value = "student/{id}")
	public ResponseEntity<Student> deleteStudent(@PathVariable("id") Integer id){
		if(!studentRepository.existsById(id))
			return new ResponseEntity<Student>(HttpStatus.NO_CONTENT);
		studentRepository.deleteById(id);

		return new ResponseEntity<Student>(HttpStatus.OK);
	}

	@ApiOperation(value = "Upisuje studenta  u bazu podataka")
	@PostMapping(value = "student")
	public ResponseEntity<Void> insertStudent(@RequestBody Student student){
		if(studentRepository.existsById(student.getId()))
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		studentRepository.save(student);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@ApiOperation(value = "Modifikuje postojećeg studenta u bazi podataka")
	@PutMapping(value = "student")
	public ResponseEntity<Void> updateStudent(@RequestBody Student student){
		if(!studentRepository.existsById(student.getId()))
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		studentRepository.save(student);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
