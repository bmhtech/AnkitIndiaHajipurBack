package com.AnkitIndia.jwtauthentication.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Business_unit")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)

public class Business_unit {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 
	
	@Column(columnDefinition="varchar(20) ")
	private String businessunit_id;
	
	@Column(columnDefinition="varchar(100) ")
	private String businessunit_name;
	
	@Column(columnDefinition="varchar(20) ")
	private String company_id;
	
	@Column(columnDefinition="varchar(20) ")
	private String modified_type;
	
}
