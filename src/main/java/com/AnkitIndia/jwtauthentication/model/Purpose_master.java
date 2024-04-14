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
@Table(name="Purpose_master")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
public class Purpose_master extends CommonProperties{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	//testsss
	@Column(columnDefinition="varchar(50) default '0'")
	private String purpose_id;
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String purpose_name;
	
	@Column(columnDefinition="varchar(250) default '0'")
	private String purpose_desc;
	
}
