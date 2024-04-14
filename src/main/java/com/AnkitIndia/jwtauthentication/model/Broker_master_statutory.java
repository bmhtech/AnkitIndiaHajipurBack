package com.AnkitIndia.jwtauthentication.model;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
@Table(name="Broker_master_statutory")
public class Broker_master_statutory extends CommonProperties {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String broker_Id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String broker_code;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean registered;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String pan_no;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String gst_no;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String cin_no;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String tan_no;
	
	@OneToOne(cascade= CascadeType.ALL)
	@JoinColumn(name = "b_Id")
	private Broker_master broker_master;

}
