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
@Table(name="GatepassGateoutAuthorization")
public class GatepassGateoutAuthorization extends CommonProperties{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(columnDefinition="varchar(50) default 'NA'")
	private String gp_go_auth_id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String vechileid;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String vechile_no;
	
	@Column(columnDefinition="varchar(150) default 'NA'")
	private String vehicle_verification;
	
	@Column(columnDefinition="varchar(200) default 'NA'")
	private String remarks;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String confirmed_by;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String gate_outstatus;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="gatepassGateoutAuthorization",cascade = CascadeType.ALL)
	private Set<GatepassGateoutAuthorization_details> gatepassGateoutAuthorization_details;
	
}
