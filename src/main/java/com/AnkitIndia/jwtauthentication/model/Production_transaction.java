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
@Table(name="Production_transaction")
public class Production_transaction extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String prod_trans_id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String prod_trans_code;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String prod_trans_date;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String business_unit;
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String business_unitname;
    
	@Column(columnDefinition="varchar(50) default '0'")
	private String shop_floor;
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String shop_floorname;
    
	@Column(columnDefinition="varchar(500) default '0'")
	private String prod_process;
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String prod_processname;
    
	@Column(columnDefinition="varchar(50) default '0'")
	private String prod_desc;
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String prod_description;
    
	@Column(columnDefinition="varchar(50) default '0'")
	private String prod_type;
    
	@Column(columnDefinition="varchar(50) default '0'")
	private String entry_mode;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String prod_shift_date;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String io_ratio;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String dev_percent;
	
	
	@Column(columnDefinition="int(1) default '0'")
	private int export;	
		
	@Column(columnDefinition="varchar(1000) default 'NA'")
	private String response;
	
	//Dynamic
	@OneToMany(fetch = FetchType.EAGER,mappedBy="production_transaction",cascade = CascadeType.ALL)
	private Set<Production_transaction_input_details> production_transaction_input_details;
	
	//Dynamic
	@OneToMany(fetch = FetchType.EAGER,mappedBy="production_transaction",cascade = CascadeType.ALL)
	private Set<Production_transaction_output_details> production_transaction_output_details;
}
