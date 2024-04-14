package com.AnkitIndia.jwtauthentication.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name="Solar_power_generation_with_powercut_dtls")
public class Solar_power_generation_with_powercut_dtls extends CommonProperties{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String slno;
	
	@Column(columnDefinition="varchar(30) default 'NA'")
	private String solar_powercut_id;
	
	@Column(columnDefinition="varchar(30) default 'NA'")
	private String from_time;
	
	@Column(columnDefinition="varchar(30) default 'NA'")
	private String to_time;
	
	@Column(columnDefinition="varchar(30) default 'NA'")
	private String total_time;
	
	@Column(columnDefinition="varchar(30) default 'NA'")
	private String power_triping;
	
	@ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "sp_id")
    private Solar_power_generation_with_powercut solar_power_generation_with_powercut;
	
}
