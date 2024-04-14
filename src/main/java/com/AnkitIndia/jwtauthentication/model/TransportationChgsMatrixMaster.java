package com.AnkitIndia.jwtauthentication.model;

import java.sql.Date;
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

@Entity
@Table(name="TransportationChgsMatrixMaster")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)

public class TransportationChgsMatrixMaster extends CommonProperties {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String tcm_id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String tcm_code;
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String tcm_description;
	
	//@Column(columnDefinition="varchar(20) default '0'")
	private Date tcm_effectivedate;

	@Column(columnDefinition="tinyint(1) default 0")
	private boolean tcm_active;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String businessunit_code;
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String businessunit_name;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String trans_mode;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean trans_charges_appl;
    
	@Column(columnDefinition="varchar(50) default '0'")
	private String gst_pay_own_rev_charges;
	
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="transportationChgsMatrixMaster",cascade = CascadeType.ALL)
	private Set<Transportation_chgs_matrix_details>  transportation_chgs_matrix_details;
}
