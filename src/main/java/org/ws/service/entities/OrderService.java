package org.ws.service.entities;

import java.util.Date;

import org.ws.service.enums.EnumServiceStatus;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

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
public class OrderService {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Date dateLine;
	private Date beginDate;
	private Date periode;
	private EnumServiceStatus status;
	private double price;
	private int quantity;
	private double totalPrice;
	@ManyToOne
	private AService service;
	@ManyToOne
	@JsonProperty(access = Access.WRITE_ONLY)
	private AOrder order;
}
