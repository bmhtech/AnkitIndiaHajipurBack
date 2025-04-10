package com.AnkitIndia.jwtauthentication.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Vehicle_master")

//@EntityListeners(AuditingEntityListener.class)

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
public class VehicleMaster extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String vehicle_id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String vehicle_no;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean vehicle_active;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String vehtype_code;
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String vehtype_name;

	@Column(columnDefinition="varchar(50) default '0'")
	private String vehicle_aliasno;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String vehicle_chassisno;
	
	private double tareweight_qty;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String tareweight_uom;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String tareweight_uomname;
	
	@Column(columnDefinition = "Decimal(10,3) default 0.000")
	private double load_capacity;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String loadcapacity_uom;
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String onwer_name;
	
	@Column(columnDefinition="varchar(250) default '0'")
	private String onwer_address;
	
	@Column(columnDefinition="bigint(10) default '0'")
	private Long onwer_phoneno;

	@Column(columnDefinition="varchar(50) default 'NA'")
	private String transporter;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean weighment_status;
	
	@Column(columnDefinition="int(10) default '0'")
	private int weighment_vehicle;
	
	@Column(columnDefinition="varchar(30) default 'NA'")
	private String weight_bridge_location;

	@OneToMany(fetch = FetchType.EAGER,mappedBy="vehicle_master",cascade = CascadeType.ALL)
	private Set<Vehicle_master_doc_details> vehicle_master_doc_details;
	
}
