package rva.jpa;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * The persistent class for the porudzbina database table.
 *
 */
@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@NamedQuery(name="Departman.findAll", query="SELECT d FROM Departman d")
public class Departman implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "DEPARTMAN_ID_GENERATOR", sequenceName = "DEPARTMAN_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DEPARTMAN_ID_GENERATOR")
	private Integer id;


	private String naziv;

	private String oznaka;

	//bi-directional many-to-one association to Dobavljac
	@ManyToOne
	@JoinColumn(name="fakultet")
	private Fakultet fakultet;

	//bi-directional many-to-one association to student
	@OneToMany(mappedBy = "departman")
	@JsonIgnore
	private List<Student> students;

	public Departman() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


  public String getNaziv() {
		return this.naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

  public String getOznaka() {
		return this.oznaka;
	}

	public void setOznaka(String oznaka) {
		this.oznaka = oznaka;
	}


	public Fakultet getFakultet() {
		return this.fakultet;
	}

	public void setFakultet(Fakultet fakultet) {
		this.fakultet = fakultet;
	}

	public List<Student> getStudents() {
		return this.students;
	}

	public void setStudent(List<Student> students) {
		this.students = students;
	}

	public Student addStudent(Student student) {
		getStudents().add(student);
		student.setDepartman(this);

		return student;
	}

	public Student removeStudent(Student student) {
		getStudents().remove(student);
		student.setDepartman(null);

		return student;
	}

}
