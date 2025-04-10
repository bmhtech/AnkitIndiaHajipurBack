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
@Table(name="Vehicle_other_weighment_load_unload")
public class Vehicle_other_weighment_load_unload extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(30) default 'NA'")
	private String vehicle_id;
	
	@Column(columnDefinition="varchar(30) default 'NA'")
	private String vehicle_no;
	
	@Column(columnDefinition="varchar(30) default 0")
	private String reference_id;
	
	@Column(columnDefinition="varchar(20) default 'NA'")
	private String ref_name;
	
	private int weighment_status;
	
	private int load_unload_status;
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String weighment_id;
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String ref_name_type;
	
	private int stock_grn_status;
	
	@Column(columnDefinition = "varchar(30) default 'NA'")
	private String gatepass_status;

	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String gp_go_id;
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String gp_go_auth_id;
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String gp_gi_id;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean we_req;
	
	@Column(columnDefinition="varchar(30) default 'NA'")
	private String weight_bridge_location;
	
	@OneToOne(cascade= CascadeType.ALL)
	@JoinColumn(name = "wmulwo_id")
    private Wm_unload_wgmnt wm_unload_wgmnt;
}