package com.AnkitIndia.jwtauthentication.model;

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
@Table(name="Binreportdetails")
public class Binreportdetails extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String binreportid;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String bingroupid;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String bingroupname;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String binid;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String binname;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String dip;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String mt;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String prevdip;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String prevmt;
	
	@ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "bn_id")
    private Binreport binreport;
	

}
