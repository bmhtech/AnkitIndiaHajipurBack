package com.AnkitIndia.jwtauthentication.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Otherpartner_group")

public class Otherpartner_group extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String op_id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String op_code;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String op_type;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String op_grp_name;
	
	@NotNull
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean op_active;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String control_acc;	
	
}
