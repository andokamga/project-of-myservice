package org.ws.service.entities;

import org.ws.service.enums.EnumVisualType;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity 
@Data 
@AllArgsConstructor 
@NoArgsConstructor
@Builder
public class Visual {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(length =50)
	private String name;
	@Column(length =50)
	private String title;
	@Column(length =50)
	private String visualLink;
	private EnumVisualType type;
	@ManyToOne
	@JsonProperty(access = Access.WRITE_ONLY)
	private Paragraph paragraph;
}
