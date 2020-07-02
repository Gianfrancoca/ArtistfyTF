package pe.edu.upc.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

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
	
	@Positive(message = "El valor tiene que ser positivo")
	@Min(value=10000000,message = "Solo se puede tener 8 digitos")
	@Max(value=99999999, message = "Solo se puede tener 8 digitos")
	@NotNull(message="El dni es obligatorio")
	@Column(name = "dni", nullable = false)
	private int dni;
	
	@Email(message="No cuenta con el formato de email.")
	@NotEmpty(message="El email es obligatorio")
	@Column(name = "email",nullable = false, length=50)
	private String email;
	
	@Positive(message = "El valor tiene que ser positivo")
	@Min(value=100000000,message = "Solo se puede tener 9 digitos")
	@Max(value=999999999, message = "Solo se puede tener 9 digitos")
	@NotNull(message="El telefono es obligatorio")
	@Column(name = "phone", nullable = false)
	private int phone;

	private String foto;

	public Organizer() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Organizer(int idOrganizer, String firstName, String lastName, int dni, String email,int phone, String foto) {
		super();
		this.idOrganizer = idOrganizer;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dni = dni;
		this.email = email;
		this.phone = phone;
		this.foto=foto;
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
	
	public String getFoto() {
		return foto;
	}


	public void setFoto(String foto) {
		this.foto = foto;
	}

	
}
