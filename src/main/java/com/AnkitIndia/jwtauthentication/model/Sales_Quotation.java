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
@Table(name="Sales_Quotation")
public class Sales_Quotation extends CommonProperties {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String quotation_id;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String quotation_no;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String quo_type;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String quotation_date;
	
	@Column(columnDefinition="varchar(20) default 'dd-mm-yyyy'")
	private String valid_till;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String price_term;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String pro_order;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String cust_channel;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String cust_ref;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String receipt_ct;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String we_uom;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String business_unit;
	
	@Column(columnDefinition="varchar(20) default 'dd-mm-yyyy'")
	private String delivery;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String q_status;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String shipment_mode;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String ref_type;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String sales_person;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String delivery_term;
	
	/*@Column(columnDefinition="tinyint(1) default 0")
	private boolean multi_load_ad;*/
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean brokerage_app;
	
	@Column(columnDefinition="varchar(200) default 'NA'")
	private String remarks;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String confirmed_by;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String approved;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String reason;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String app_chgs_id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String inv_type;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String referance_id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String customer;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String customername;

	@Column(columnDefinition="varchar(50) default 'NA'")
	private String quotationcheckpoint;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean sale_orderused;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean terminate;
	
	//Dynamic
	@OneToMany(fetch = FetchType.EAGER,mappedBy="sQuotation",cascade = CascadeType.ALL)
	private Set<Sales_Quotation_Item_Dtls> sales_Quotation_Item_Dtls;
	
	//Dynamic
	@OneToMany(fetch = FetchType.EAGER,mappedBy="sQuotation",cascade = CascadeType.ALL)
	private Set<Sales_Quotation_Broker_Dtls> sales_Quotation_Broker_Dtls;
	
	//Static
	@OneToOne(fetch = FetchType.EAGER,mappedBy="sQuotation",cascade =CascadeType.ALL)
	private Sales_Quotation_Summary sales_Quotation_Summary;
	
	//Dynamic
	@OneToMany(fetch = FetchType.EAGER,mappedBy="sQuotation",cascade = CascadeType.ALL)
	private Set<Sales_Quotation_Summary_dyn> sales_Quotation_Summary_dyn;
	
	//Static
	@OneToOne(fetch = FetchType.EAGER,mappedBy="sQuotation",cascade =CascadeType.ALL)
	private Sales_Quotation_Trans_Info sales_Quotation_Trans_Info;
	
	//Static
	@OneToOne(fetch = FetchType.EAGER,mappedBy="sQuotation",cascade =CascadeType.ALL)
	private Sales_Quotation_Terms_Con sales_Quotation_Terms_Con;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="sQuotation",cascade = CascadeType.ALL)
	private Set<Sales_Quotation_Party_Dtls> sales_Quotation_Party_Dtls;
	
	//Static
	@OneToOne(fetch = FetchType.EAGER,mappedBy="sQuotation",cascade =CascadeType.ALL)
	private Sales_Quotation_Shipment_Dtls sales_Quotation_Shipment_Dtls;
	
	//Dynamic
	@OneToMany(fetch = FetchType.EAGER,mappedBy="sQuotation",cascade = CascadeType.ALL)
	private Set<Sales_Quotation_Docs> sales_Quotation_Docs;
	
	//Static
	/*@OneToOne(fetch = FetchType.EAGER,mappedBy="sQuotation",cascade =CascadeType.ALL)
	private Sales_Quotation_Termination sales_Quotation_Termination;*/
}
