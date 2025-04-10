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
@Table(name="Wm_loading_advice")
public class Wm_loading_advice extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String advice_id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String advice_type;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String advice_no;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String adviceno;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String advice_date;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String advicedate;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String bus_partner;
	
	/*@Column(columnDefinition="tinyint(1) default 0")
	private boolean wereq_active;*/
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String b_unit;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String load_point;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String vehicle_id;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String vehicle_no;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String load_by;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String erp_usr_name;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String ref_doc_type;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String delivery_business_unit;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String unloading_point;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String doc_no;
	
	private Date doc_date;
	
	/*@Column(columnDefinition="tinyint(1) default 0")
	private boolean wchrgapp_active;*/
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String remarks;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String confirmed_by;
    
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String approval;
    
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String reason;
	
	@Column(columnDefinition = "varchar(50) default 0")
	private String price_term;
	
	@Column(columnDefinition = "varchar(50) default 0")
	private String cust_refdocno;
	
	@Column(columnDefinition = "varchar(50) default 0")
	private String pur_cust_refdocno;
	
	@Column(columnDefinition = "varchar(50) default 0")
	private String pur_cust_refdocnoqty;
	
	
	private int loading_status;
	
	private int weighment_status;
	
	@Column(columnDefinition = "varchar(50) default 0")
	private String weighment_id;
	
	@Column(columnDefinition = "varchar(50) default 0")
	private String referance_id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String customer;
	
	@Column(columnDefinition = "varchar(100) default 'NA'")
	private String customer_name;
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String supplier;
	
	@Column(columnDefinition = "varchar(100) default 'NA'")
	private String supplier_name;
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String billing_req;
	
	@Column(columnDefinition = "varchar(50) default 'No'")
	private String stk_trans_chln_status;
	
	@Column(columnDefinition = "varchar(50) default 'No'")
	private String stk_trans_inv_status;
	
	@Column(columnDefinition = "varchar(50) default 'Not Done'")
	private String unload_status;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean delvchallan_status;
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String staticuom;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean multipleloading;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean jobwork;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean looseitem;
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String payment_mode;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean terminate;
	 
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String terminated_by;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean refraction;
	
	@Column(columnDefinition="varchar(30) default 'NA'")
	private String weight_bridge_location;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy="wm_loading_advice",cascade = CascadeType.ALL)
	private Set<Wm_loading_advice_itm_dtls> wm_loading_advice_itm_dtls;
	
	@OneToOne(fetch = FetchType.LAZY,mappedBy="wm_loading_advice",cascade = CascadeType.ALL)
	private Wm_loading_advice_driver_dtls wm_loading_advice_driver_dtls;
	
	@OneToOne(fetch = FetchType.LAZY,mappedBy="wm_loading_advice",cascade = CascadeType.ALL)
	private Wm_loading_advice_trans_info wm_loading_advice_trans_info;

	@OneToOne(fetch = FetchType.LAZY,mappedBy="wm_loading_advice",cascade = CascadeType.ALL)
	private Wm_loading_advice_bp_dtls wm_loading_advice_bp_dtls;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy="wm_loading_advice",cascade = CascadeType.ALL)
	private Set<Wm_loading_advice_doc_attch> wm_loading_advice_doc_attch;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy="wm_loading_advice",cascade =CascadeType.ALL)
	private Set<Wm_loading_advice_broker_dtls> wm_loading_advice_broker_dtls;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy="wm_loading_advice",cascade =CascadeType.ALL)
	private Set<Wm_loading_advice_Party_Dtls> wm_loading_advice_Party_Dtls;
	
	@OneToOne(fetch = FetchType.LAZY,mappedBy="wm_loading_advice",cascade = CascadeType.ALL)
	private Wm_loading_advice_Shipment_Dtls wm_loading_advice_Shipment_Dtls;
	
	@OneToOne(fetch = FetchType.LAZY,mappedBy="wm_loading_advice",cascade = CascadeType.ALL)
	private Vehicle_weighment_load_unload vehicle_weighment_load_unload;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="wm_loading_advice",cascade = CascadeType.ALL)
	private Set<Wm_loading_advice_Item_Dtls_for_jobwork> wm_loading_advice_Item_Dtls_for_jobwork;
}
