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
@Table(name="GatepassGetin_details")
public class GatepassGetin_details extends CommonProperties{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(columnDefinition="varchar(50) default 'NA'")
	private String gp_gi_id;
	
	private int sl_no;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String checklist_code;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String checkin;
	
	@Column(columnDefinition="varchar(150) default 'NA'")
	private String description;
	
	@ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "gpi_Id")
	private GatepassGetin gatepassGetin;
}
