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
@Table(name="Bom_master")
public class Bom_master extends CommonProperties{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String production_id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String production_code;
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String business_unit;
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String business_unitname;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String shop_floor;
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String shop_floorname;
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String prod_desc;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String prod_type;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String entry_mode;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String io_ratio;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String prod_uom;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String dev_percent;
	
	@Column(columnDefinition="varchar(500) default '0'")
	private String prod_process;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String ratio_applicable;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String applicable_charges_id;
	
	@Column(columnDefinition = "Double(10,2) default 0.00" )
	private double outtotratio;
	
	@Column(columnDefinition = "Double(10,2) default 0.00" )
	private double totratio;
	
	
	//Dynamic
	@OneToMany(fetch = FetchType.EAGER,mappedBy="bom_master",cascade = CascadeType.ALL)
	private Set<Bom_input_details> bom_input_details;
	
	//Dynamic
	@OneToMany(fetch = FetchType.EAGER,mappedBy="bom_master",cascade = CascadeType.ALL)
	private Set<Bom_output_details> bom_output_details;
	
	//Dynamic
	@OneToMany(fetch = FetchType.EAGER,mappedBy="bom_master",cascade = CascadeType.ALL)
	private Set<Bom_resource_cost> bom_resource_cost;
}
