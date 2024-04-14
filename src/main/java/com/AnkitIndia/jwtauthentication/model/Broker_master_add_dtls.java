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
@Table(name="Broker_master_add_dtls")
public class Broker_master_add_dtls extends CommonProperties {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String broker_Id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String broker_code;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String contact_person;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String designation;
	
	private Long tell_no;
	
	private Long mob_no;
	
	@Column(columnDefinition="varchar(20) default 'NA'")
	private String fax_no;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String email;
	
	@ManyToOne(cascade= CascadeType.ALL)
	@JoinColumn(name = "b_Id")
	private Broker_master broker_master;
	

}
