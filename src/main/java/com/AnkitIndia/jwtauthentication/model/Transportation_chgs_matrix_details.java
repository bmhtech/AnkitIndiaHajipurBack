package com.AnkitIndia.jwtauthentication.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Transportation_chgs_matrix_details")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
public class Transportation_chgs_matrix_details extends CommonProperties {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String trans_charge_code;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String tcm_id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String tcm_code;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String transportation_from;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String transportation_to;
	
	private Long distance_in_km;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String vehicles_type;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String full_truck_load_rate;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String rate_uom;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String uom;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String tax_code;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String tax_rate;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String transportation_acc;
	
	/*@Column(columnDefinition="varchar(50) default 'NA'")
	private String tds_code;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String tds_acc;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String tds_rate;*/
	
	@Column(columnDefinition="TEXT")
	private String [] transporter;
	
	@Lob
	private String transporter_array;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String allowed_shortage;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String deduction_basedon;
	
	@ManyToOne(cascade= CascadeType.ALL)
	@JoinColumn(name="T_C_Id")
	private TransportationChgsMatrixMaster transportationChgsMatrixMaster;

	
}
