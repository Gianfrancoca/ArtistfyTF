package pe.edu.upc.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "artists")
public class Artist implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idArtist;
	
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
	
	@NotEmpty(message="La habilidad es obligatorio")
	@Pattern(regexp = "[a-zA-Z]+", message="La habilidad solo puede tener letras.")
	@Column(name = "skill",nullable = false, length=50)
	private String skill;
	
	//@NotEmpty(message="El genero es obligatorio")
	//@Pattern(regexp = "[a-zA-Z]+", message="El genero solo puede tener letras.")
	//@Column(name = "genre",nullable = false, length=50)
	//private String genre;
	
	@ManyToOne
	@JoinColumn(name = "idGenre", nullable = false)
	private Genre genre;
	
	@NotEmpty(message="La url es obligatorio")
	@Pattern(regexp = "[a-zA-Z].+", message="La url solo puede tener letras.")
	@Column(name = "websiteUrl",nullable = false, length=50)
	private String websiteUrl;

	public Artist() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Artist(int idArtist,
			@NotEmpty(message = "El nombre es obligatorio") @Pattern(regexp = "[a-zA-Z]+", message = "El nombre solo puede tener letras.") String firstName,
			@NotEmpty(message = "El apellido es obligatorio") @Pattern(regexp = "[a-zA-Z]+", message = "El apellido solo puede tener letras.") String lastName,
			@Positive(message = "El valor tiene que ser positivo") @Min(value = 10000000, message = "Solo se puede tener 8 digitos") @Max(value = 99999999, message = "Solo se puede tener 8 digitos") @NotNull(message = "El dni es obligatorio") int dni,
			@Email(message = "No cuenta con el formato de email.") @NotEmpty(message = "El email es obligatorio") String email,
			@Positive(message = "El valor tiene que ser positivo") @Min(value = 100000000, message = "Solo se puede tener 9 digitos") @Max(value = 999999999, message = "Solo se puede tener 9 digitos") @NotNull(message = "El telefono es obligatorio") int phone,
			@NotEmpty(message = "La habilidad es obligatorio") @Pattern(regexp = "[a-zA-Z]+", message = "La habilidad solo puede tener letras.") String skill,
			Genre genre,
			@NotEmpty(message = "La url es obligatorio") @Pattern(regexp = "[a-zA-Z].+", message = "La url solo puede tener letras.") String websiteUrl) {
		super();
		this.idArtist = idArtist;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dni = dni;
		this.email = email;
		this.phone = phone;
		this.skill = skill;
		this.genre = genre;
		this.websiteUrl = websiteUrl;
	}

	public int getIdArtist() {
		return idArtist;
	}

	public void setIdArtist(int idArtist) {
		this.idArtist = idArtist;
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

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public String getWebsiteUrl() {
		return websiteUrl;
	}

	public void setWebsiteUrl(String websiteUrl) {
		this.websiteUrl = websiteUrl;
	}

	
	
	
}
