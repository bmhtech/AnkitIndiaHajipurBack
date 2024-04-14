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
@Table(name="Wm_unload_advice")
public class Wm_unload_advice extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String unadviceid;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String unadviceno;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String item_type;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String item_subtype;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String item_subtypename;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String busi_partner;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String customer;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String advice_type;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String ref_type;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean we_req;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean we_chg_app;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String supp_ref_doc;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String supp_ref_docno;
	
	private Date supp_ref_doc_date;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String ula_date;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String uladate;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean grn_req;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String business_unit;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String business_unitname;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String transporter_code;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String transporter_name;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String vehicle_id;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String vehicle_no;
	
	//private int total_qty;
	@Column(columnDefinition = "Decimal(10,3) default 0.000")
	private double total_qty;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String uom;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean return_type;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String return_remarks;
	
	@Column(columnDefinition="varchar(250) default 'NA'")
	private String remarks;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean brokerage_active;

	@Column(columnDefinition="varchar(50) default 'NA'")
	private String app_chgs_id;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean qc_required;
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String referance_id;
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String weighment_id;
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String adv_po_tag_no;
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String adv_po_tag_date;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String supp_name;
	
	private int unload_status;
	
	private int weighment_status;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String pur_orderid;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String username;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String passing_wt;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean itemtype;
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String purchase_subtype;
	
	@Column(columnDefinition="varchar(20) default 'NA'")
	private String uadvice_status; /* New 14-04-2022*/
	
	private String close_date; /* New 14-04-2022*/
	
	@Column(columnDefinition = "Decimal(10,3) default 0.000")
	private double total_qty_copy; /* New 14-04-2022*/
	
	private int grn_status;
	
	@Column(columnDefinition="varchar(10) default 'No'")
	private String pur_return_status;
	
	@Column(columnDefinition="varchar(20) default 'NA'")
	private String purreturnid;
	
	@Column(columnDefinition="varchar(20) default 'NA'")
	private String poitemnumber;
	
	@Column(columnDefinition="varchar(20) default 'NA'")
	private String qc_status;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean jobwork;
	 
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean looseitem;
	 
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean terminate;
		 
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String terminated_by;
	
	//Dynamic
	@OneToMany(fetch = FetchType.EAGER,mappedBy="wm_unload_advice",cascade = CascadeType.ALL)
	private Set<Wm_unload_advice_item_dtls> wm_unload_advice_item_dtls;
	
	//Static
	@OneToOne(fetch = FetchType.EAGER,mappedBy="wm_unload_advice",cascade =CascadeType.ALL)
	private Wm_unload_advice_party_wm wm_unload_advice_party_wm;
	
	//Static
	@OneToOne(fetch = FetchType.EAGER,mappedBy="wm_unload_advice",cascade =CascadeType.ALL)
	private Wm_unload_advice_driver_dtls wm_unload_advice_driver_dtls;
	
	//Dynamic
	@OneToMany(fetch = FetchType.EAGER,mappedBy="wm_unload_advice",cascade =CascadeType.ALL)
	private Set<Wm_unload_advice_broker_dtls> wm_unload_advice_broker_dtls;
	
	//Static
	@OneToOne(fetch = FetchType.EAGER,mappedBy="wm_unload_advice",cascade =CascadeType.ALL)
	private Wm_unload_advice_terms_con wm_unload_advice_terms_con;
	
	//Static
	@OneToOne(fetch = FetchType.EAGER,mappedBy="wm_unload_advice",cascade =CascadeType.ALL)
	private Wm_unload_advice_trans_info wm_unload_advice_trans_info;
	
	//Static
	@OneToOne(fetch = FetchType.EAGER,mappedBy="wm_unload_advice",cascade =CascadeType.ALL)
	private Wm_unload_advice_bp_dtls wm_unload_advice_bp_dtls;
	
	//Dynamic
	@OneToMany(fetch = FetchType.EAGER,mappedBy="wm_unload_advice",cascade = CascadeType.ALL)
	private Set<Wm_unload_advice_doc> wm_unload_advice_docs;
	
	//Dynamic
	@OneToMany(fetch = FetchType.EAGER,mappedBy="wm_unload_advice",cascade = CascadeType.ALL)
	private Set<Wm_unload_advice_app_chgs> wm_unload_advice_app_chgs;
	
	//Static
	@OneToOne(fetch = FetchType.EAGER,mappedBy="wm_unload_advice",cascade =CascadeType.ALL)
	private Vehicle_weighment_load_unload vehicle_weighment_load_unload;

	//Dynamic
	@OneToMany(fetch = FetchType.EAGER,mappedBy="wm_unload_advice",cascade = CascadeType.ALL)
	private Set<Wm_unload_advice_item_dtls_for_jobwork> wm_unload_advice_item_dtls_for_jobwork;
		
}
