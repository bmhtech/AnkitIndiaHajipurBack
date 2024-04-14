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
@Table(name="Delivery_challan_Trans_Info")
public class Delivery_challan_Trans_Info extends CommonProperties{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String delivery_cid;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String trans_borne_by;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String mode_of_trans;
	
	@Column(columnDefinition="varchar(100) default 0")
	private String trans_code;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String transporter_name;
	
	@Column(columnDefinition="varchar(100) default 0")
	private String charge_code;
	
	@Column(columnDefinition="varchar(100) default 0")
	private String vehle_no;
	
	@Column(columnDefinition="varchar(100) default 0")
	private String vehicle_no;
	
	@Column(columnDefinition="varchar(30) default 'NA'")
	private String transport_rate;
	
	@Column(columnDefinition = "Double(10,2)")
	private double freight_amt;
    
	@Column(columnDefinition = "Double(10,2)")
	private double adv_paid;
	
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean transportchargesadd;
	
	
	@OneToOne(cascade= CascadeType.ALL)
	@JoinColumn(name = "dc_id")
    private Delivery_challan delivery_challan;
}
