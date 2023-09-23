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
public class Town {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true, length =50,nullable = false)
	private String name;
	@Column(length =50)
	private String longitude;
	@Column(length =50)
	private String latitude;
	@Default
	@OneToMany(mappedBy = "town")
	@JsonProperty(access = Access.WRITE_ONLY)
	private List<AOrder> order  = new ArrayList<>();
}
