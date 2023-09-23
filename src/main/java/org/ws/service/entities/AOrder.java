package org.ws.service.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
public class AOrder {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long idUser;
	@Column(length =50,nullable = true)
	private String clientName;
	@Column(length =50)
	private String clientLastName;
	@Column(length =50)
	private String jobTitle;
	@Column(length =50)
	private String phoneNumber;
	private String details;
	private String description;
	private boolean billPrinte;
	private double totals;
	@ManyToOne
	private Town town;
	@Default
	@OneToMany(mappedBy = "order")
	private List<OrderService> OrderService  = new ArrayList<>();
	@Default
	@OneToMany(mappedBy = "order")
	private List<Observation> observations  = new ArrayList<>();

}
