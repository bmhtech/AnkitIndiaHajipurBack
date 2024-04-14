package com.AnkitIndia.jwtauthentication.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Solar_power_generation_with_powercut")
public class Solar_power_generation_with_powercut extends CommonProperties{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(30) default 'NA'")
	private String solar_powercut_id;
	
	@Column(columnDefinition="varchar(30) default 'NA'")
	private String b_unit;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String b_unit_name;
	
	@Column(columnDefinition="varchar(30) default 'NA'")
	private String solar_date;
	
	@Column(columnDefinition = "Double(10,3) default 0.000")
	private double generation;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String weather_condition;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy="solar_power_generation_with_powercut",cascade = CascadeType.ALL)
	private Set<Solar_power_generation_with_powercut_dtls> solar_power_generation_with_powercut_dtls;
}
