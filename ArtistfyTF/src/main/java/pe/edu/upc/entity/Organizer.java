package pe.edu.upc.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "organizers")
public class Organizer implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idOrganizer;
	
	@NotEmpty(message="El nombre es obligatorio")
	@Pattern(regexp = "[a-zA-Z]+", message="El nombre solo puede tener letras.")
	@Column(name = "firstName",nullable = false, length=50)
	private String firstName;
	
	@NotEmpty(message="El apellido es obligatorio")
	@Pattern(regexp = "[a-zA-Z]+", message="El apellido solo puede tener letras.")
	@Column(name = "lastName",nullable = false, length=50)
	private String lastName;
	

	@NotNull(message="El dni es obligatorio")
	@Column(name = "dni", nullable = false)
	private int dni;
	
	@Email(message="No cuenta con el formato de email.")
	@NotEmpty(message="El email es obligatorio")
	@Column(name = "email",nullable = false, length=50)
	private String email;
	
	
	@NotNull(message="El telefono es obligatorio")
	@Column(name = "phone", nullable = false)
	private int phone;


	public Organizer() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Organizer(int idOrganizer, String firstName, String lastName, int dni, String email,int phone) {
		super();
		this.idOrganizer = idOrganizer;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dni = dni;
		this.email = email;
		this.phone = phone;
	}


	public int getIdOrganizer() {
		return idOrganizer;
	}


	public void setIdOrganizer(int idOrganizer) {
		this.idOrganizer = idOrganizer;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public int getDni() {
		return dni;
	}


	public void setDni(int dni) {
		this.dni = dni;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public int getPhone() {
		return phone;
	}


	public void setPhone(int phone) {
		this.phone = phone;
	}
	
	
	
}
