package com.AnkitIndia.jwtauthentication.model;

import java.util.Set;

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

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Solar_power_generation")
public class Solar_power_generation extends CommonProperties{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(30) default 'NA'")
	private String solar_power_id;
	
	@Column(columnDefinition="varchar(30) default 'NA'")
	private String b_unit;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String b_unit_name;
	
	@Column(columnDefinition="varchar(30) default 'NA'")
	private String solar_date;
	
	@Column(columnDefinition = "Double(10,3) default 0.000")
	private double no_one;
	
	@Column(columnDefinition = "Double(10,3) default 0.000")
	private double no_two;
	
	@Column(columnDefinition = "Double(10,3) default 0.000")
	private double no_three;
	
	@Column(columnDefinition = "Double(10,3) default 0.000")
	private double no_four;
	
	@Column(columnDefinition = "Double(10,3) default 0.000")
	private double no_five;
	
	@Column(columnDefinition = "Double(10,3) default 0.000")
	private double no_six;
	
	@Column(columnDefinition = "Double(10,3) default 0.000")
	private double no_seven;
	
	@Column(columnDefinition = "Double(10,3) default 0.000")
	private double no_eight;
	
	@Column(columnDefinition = "Double(10,3) default 0.000")
	private double no_nine;
	
	@Column(columnDefinition = "Double(10,3) default 0.000")
	private double no_ten;
	
	@Column(columnDefinition = "Double(10,3) default 0.000")
	private double no_eleven;
	
	@Column(columnDefinition = "Double(10,3) default 0.000")
	private double total;
	
	@Column(columnDefinition="varchar(150) default 'NA'")
	private String remarks;
	
}
