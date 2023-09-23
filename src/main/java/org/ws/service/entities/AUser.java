package org.ws.service.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity 
@Data 
@AllArgsConstructor 
@NoArgsConstructor
@Builder
public class AUser {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(length =50,nullable = false)
	private String firstName;
	@Column(length =50,nullable = false)
	private String lastName;
	@Column(unique = true, length =50,nullable = false)
	private String username;
	@Column(length =20)
	private String checkMessage;
	@Column(unique = true, length =20)
	private String phoneNumber;
	//@Column(unique = true, length =50)
	private String email;
	@Column(length =50,nullable = false)
	private String password;
	@Column(length =50)
	private String countyCode;
	private Date birthDate;
	private String imageName;
	private String imageLink;
	private boolean active;
	@Default
	@ManyToMany(mappedBy = "users",fetch = FetchType.EAGER)
	private List<ARole> Roles=new ArrayList<>();
}
