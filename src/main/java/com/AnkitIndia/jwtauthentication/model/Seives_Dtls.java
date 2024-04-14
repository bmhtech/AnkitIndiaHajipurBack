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
@Table(name="Seives_Dtls")
public class Seives_Dtls extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String slno;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String seivesid;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String seives_name;

	@Column(columnDefinition="varchar(30) default 'NA'")
	private String itemid;
	
	@ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "s_id")
    private Seives_master seivesmaster;
}