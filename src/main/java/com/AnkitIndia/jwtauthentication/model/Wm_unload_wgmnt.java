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
@Table(name="Wm_unload_wgmnt")
public class Wm_unload_wgmnt extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String wgment_id;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String wgment_no;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String wgmentno;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String weight1;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String weight2;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean we_status;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String wgment_date;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String wgmentdate;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String wgment_for;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String ref_doc_no;
	
	private Date ref_doc_date;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String vehicle_id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String vehicle_no;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String veh_type;
	
	@Column(columnDefinition = "Decimal(10,3) default 0.000")
	private double gross_weight;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String gw_unit;
	
	private Date gw_date;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String gw_time;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String gw_remarks;
	
	@Column(columnDefinition = "Decimal(10,3) default 0.000")
	private double tare_weight;  
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String tw_unit;

	private Date tw_date;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String tw_time;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String tw_remarks;
	
	@Column(columnDefinition = "Decimal(10,3) default 0.000")
	private double net_weight;  
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String nw_unit;
	
	@Column(columnDefinition = "Decimal(10,3) default 0.000")
	private double digital_weight;  
	
	@Column(columnDefinition = "Decimal(10,3) default 0.000")
	private double digital_weight1;  
	
	@Column(columnDefinition = "Decimal(10,2)")
	private double wgment_charge;  
	
	@Column(columnDefinition = "Decimal(10,2)")
	private double wgment_rs;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String tarebags;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String firstbags;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String vehicle_ref_name;
	
	@Column(columnDefinition = "Decimal(10,3) default 0.000")
	private double totalweight;

	@Column(columnDefinition="varchar(50) default '0'")
	private String advice;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String advice_no;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String partyname;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String weighment_type;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String nopartyid;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String noitemid;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String nopartyname;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String noitemname;
	
	@Column(columnDefinition="varchar(200) default 'NA'")
	private String remarks;
	
	@Column(columnDefinition = "Double(12,2) default 0.00")
	private double tare_weight_bulker;
	
	@Column(columnDefinition = "Double(12,2) default 0.00")
	private double net_weight_bulker;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean terminate;
	 
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String terminated_by;
	
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String ter_oth_wgmnt_no;
	
	@Column(columnDefinition="varchar(200) default 'NA'")
	private String terminate_remarks;
	
	@Column(columnDefinition = "Double(12,2) default 0.00")
	private double shifting_price;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String wgment_no_alt;
	
	@Column(columnDefinition="varchar(30) default 'NA'")
	private String weight_bridge_location;
	
	//Dynamic
	@OneToMany(fetch = FetchType.EAGER,mappedBy="wm_unload_wgmnt",cascade = CascadeType.ALL)
	private Set<Wm_unload_wgmnt_dtls> wm_unload_wgmnt_dtls;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="wm_unload_wgmnt",cascade = CascadeType.ALL)
	private Set<Weighment_doc> weighment_doc;
	
	@OneToOne(fetch = FetchType.EAGER,mappedBy="wm_unload_wgmnt",cascade =CascadeType.ALL)
	private Vehicle_other_weighment_load_unload vehicle_other_weighment_load_unload;
	
	@OneToOne(fetch = FetchType.EAGER,mappedBy="wm_unload_wgmnt",cascade = CascadeType.ALL)
	private Wm_loading_advice_itm_dtls wm_loading_advice_itm_dtls;
	
	@OneToOne(fetch = FetchType.EAGER,mappedBy="wm_unload_wgmnt",cascade = CascadeType.ALL)
	private Wm_loading_advice_Item_Dtls_for_jobwork wm_loading_advice_Item_Dtls_for_jobwork;
}
