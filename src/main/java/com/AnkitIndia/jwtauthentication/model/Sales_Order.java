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
@Table(name="Sales_Order")
public class Sales_Order extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String order_id;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String order_no;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String orderno;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String pro_order;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String order_date;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String orderdate;
	
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String ref_type;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String business_unit;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String order_type;
	
	/*@Size(max = 50)
	private String sales_type;*/
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String valid_till;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String price_term;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String cust_channel;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String cust_refdocno;
	
	/*private Date ref_date;*/
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String receipt_criteria;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String q_status;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String we_uom;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String delivery_date;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String shipment_mode;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String sales_person;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String delivery_term;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String app_chgs_id;
	
	@Column(columnDefinition="varchar(250) default 'NA'")
	private String remarks;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String confirmed_by;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String approval;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String reason;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String inv_type;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String referance_id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String customer;
	
	
	
	@Column(columnDefinition="varchar(10) default 'NA'")
	private String tolerancecheckpoint;

	@Column(columnDefinition="varchar(250) default 'NA'")
	private String tolerancecheckpointremarks;
	
	/*@Size(max = 50)
	private String party;
	
	@Size(max = 50)
	private String dalivery_days;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean bro_info;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean acc_norms_app;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean closed_sl_order;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean supp_bill_req;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean weighment;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean multi_load_ad;*/
	

	@Column(columnDefinition="tinyint(1) default 0")
	private boolean brokerage_app;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean loading_status;
	
	 @Column(columnDefinition="varchar(50) default 'NA'")
	 private String salereturn_id;
	 
	 @Column(columnDefinition="varchar(10) default 'NO'")
	 private String sales_returnstatus;
	 
	 @Column(columnDefinition="varchar(50) default 'NA'")
	 private String cust_ref_doc_date;
	 
	 @Column(columnDefinition="varchar(50) default 'No'")
	 private String trans_borne_by_chgs;
	 
	@Column(columnDefinition="TEXT")
	private String cust_channel_list;
	 
	 
	 @Column(columnDefinition="varchar(50) default 'NA'")
	 private String ratedateentry;
	 
	 @Column(columnDefinition="varchar(50) default 'NA'")
	 private String total_job_amt;
	 
	 @Column(columnDefinition="tinyint(1) default 0")
	 private boolean terminate;
	
	//Dynamic
	@OneToMany(fetch = FetchType.LAZY,mappedBy="sales_order",cascade = CascadeType.ALL)
	private Set<Sales_Order_Item_Dtls> sales_Order_Item_Dtls;
	
	//Dynamic
	@OneToMany(fetch = FetchType.LAZY,mappedBy="sales_order",cascade = CascadeType.ALL)
	private Set<Sales_Order_Broker_Dtls> sales_Order_Broker_Dtls;
	
	//Static
	@OneToOne(fetch = FetchType.LAZY,mappedBy="sales_order",cascade =CascadeType.ALL)
	private Sales_Order_Summary sales_Order_Summary;
	
	//Dynamic
	@OneToMany(fetch = FetchType.LAZY,mappedBy="sales_order",cascade = CascadeType.ALL)
	private Set<Sales_Order_Summary_dyn> sales_Order_Summary_dyn;
	
	
	//Static
	@OneToOne(fetch = FetchType.LAZY,mappedBy="sales_order",cascade =CascadeType.ALL)
	private Sales_Order_Trans_Info sales_Order_Trans_Info;
	
	//Static
	@OneToMany(fetch = FetchType.LAZY,mappedBy="sales_order",cascade =CascadeType.ALL)
	private Set<Sales_Order_Party_Dtls> sales_Order_Party_Dtls;
	
	//Static
	@OneToOne(fetch = FetchType.LAZY,mappedBy="sales_order",cascade =CascadeType.ALL)
	private Sales_Order_Shipment_Dtls sales_Order_Shipment_Dtls;
	
	//Static
	@OneToOne(fetch = FetchType.LAZY,mappedBy="sales_order",cascade =CascadeType.ALL)
	private Sales_Order_Terms_Con sales_Order_Terms_Con;
				
	//Dynamic
	@OneToMany(fetch = FetchType.LAZY,mappedBy="sales_order",cascade = CascadeType.ALL)
	private Set<Sales_Order_Docs> sales_Order_Docs;			
		
	//Static
	/*@OneToOne(fetch = FetchType.EAGER,mappedBy="sales_order",cascade =CascadeType.ALL)
	private Sales_Order_Termination sales_Order_Termination;*/
	
	//Dynamic
	@OneToMany(fetch = FetchType.EAGER,mappedBy="sales_order",cascade = CascadeType.ALL)
	private Set<Sales_Order_Trans_Chgs_dyn> sales_Order_Trans_Chgs_dyn;
	
	//Dynamic
	@OneToMany(fetch = FetchType.EAGER,mappedBy="sales_order",cascade = CascadeType.ALL)
	private Set<Sales_Order_Item_Dtls_for_jobwork> sales_Order_Item_Dtls_for_jobwork;
	
	//Dynamic
	@OneToMany(fetch = FetchType.EAGER,mappedBy="sales_order",cascade = CascadeType.ALL)
	private Set<Sales_Order_Item_Dtls_for_jobwork_price> sales_Order_Item_Dtls_for_jobwork_price;
	
}
