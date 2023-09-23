package org.ws.service.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

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
public class AService {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private Long level;
	@Column(length =50,nullable = true)
	private String title;
	private boolean disponiblility;
	private double minPrice;
	private double maxPrice;
	private int like;
	private int unLike;
	private int rate;
	private boolean statusRate;
	private double ratePrice;
	private boolean available;
	@ManyToOne
	private Category category;
	@ManyToOne
	private AService service;
	@Default
	@OneToMany(mappedBy = "service")
	private List<AService> services = new ArrayList<>();
	@Default
	@OneToMany(mappedBy = "service")
	private List<Paragraph> Paragraphs  = new ArrayList<>();
	@Default
	@OneToMany(mappedBy = "service")
	@JsonProperty(access = Access.WRITE_ONLY)
	private List<OrderService> OrderService  = new ArrayList<>();
	
}
