package pe.edu.upc.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "artists")
public class Artist implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idArtist;
	
	@Column(name = "firstName",nullable = false, length=50)
	private String firstName;
	
	@Column(name = "lastName",nullable = false, length=50)
	private String lastName;
	
	private Long dni;
	
	@Column(name = "email",nullable = false, length=50)
	private String email;
	
	private Long phone;
	
	private String skill;
	
	@Column(name = "genre",nullable = false, length=50)
	private String genre;
	
	@Column(name = "websiteUrl",nullable = false, length=50)
	private String websiteUrl;

	public Artist() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Artist(int idArtist, String firstName, String lastName, Long dni, String email, Long phone, String skill,
			String genre, String websiteUrl) {
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

	public Long getDni() {
		return dni;
	}

	public void setDni(Long dni) {
		this.dni = dni;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getPhone() {
		return phone;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
	}

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getWebsiteUrl() {
		return websiteUrl;
	}

	public void setWebsiteUrl(String websiteUrl) {
		this.websiteUrl = websiteUrl;
	}
	
	
}
