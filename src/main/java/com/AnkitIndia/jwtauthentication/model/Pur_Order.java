package com.AnkitIndia.jwtauthentication.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Pur_Order")
public class Pur_Order extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(columnDefinition="varchar(20) default 'NA'")
	private String pur_orderid;
	
	@Column(columnDefinition="varchar(30) default 'NA'")
	private String pur_order_no;

	@Column(columnDefinition="varchar(30) default 'NA'")
	private String purorderno;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean ser_item_type;
	
	@Size(max = 20)
	private String ord_date;

	@Size(max = 20)
	private String orddate;
	
	@Column(columnDefinition="varchar(20) default 'NA'")
	private String ser_item_subtype;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String ser_item_subtype_name;
	
	@Column(columnDefinition="varchar(30) default 'NA'")
	private String pur_ord_type;
	
	@Column(columnDefinition="varchar(20) default 'NA'")
	private String supplier_name;
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String supplier;

	@Column(columnDefinition="varchar(20) default 'NA'")
	private String businessunit;
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String businessunit_name;
	
	@Column(columnDefinition="varchar(20) default 'NA'")
	private String advice_req;
	
	@Column(columnDefinition="varchar(20) default 'NA'")
	private String po_fullfillment;

	private int no_of_advice;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String referance_type;

	@Column(columnDefinition="varchar(50) default 'NA'")
	private String pref_doc_no;

	@Column(columnDefinition="tinyint(1) default 0")
	private boolean master_roll_required;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean broker_info; /* new 16-04-2022 */
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean madvice_sin_grn;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean weightment_req;
	
	@Column(columnDefinition="varchar(20) default 'NA'")
	private String pan_no;

	@Column(columnDefinition="varchar(20) default 'NA'")
	private String gst_no;

	@Column(columnDefinition="varchar(30) default 'NA'")
	private String cin_no;

	@Column(columnDefinition="varchar(30) default 'NA'")
	private String tan_no;

	@Column(columnDefinition="varchar(20) default 'NA'")
	private String ship_to_addr_id;

	@Column(columnDefinition="varchar(200) default 'NA'")
	private String ship_to_addr;

	@Column(columnDefinition="varchar(20) default 'NA'")
	private String pay_to_addr_id;

	@Column(columnDefinition="varchar(200) default 'NA'")
	private String pay_to_addr;

	@Column(columnDefinition="TEXT")
	private String remarks;

	@Column(columnDefinition="varchar(50) default 'NA'")
	private String confirmed_by;

	@Column(columnDefinition="varchar(20) default 'NA'")
	private String approved;

	@Column(columnDefinition="varchar(20) default 'NA'")
	private String reason;

	@Column(columnDefinition="varchar(200) default 'NA'")
	private String app_remarks;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean brokerage_active;
	
	@Column(columnDefinition="varchar(20) default 'NA'")
	private String app_chgs_id;
	
	@Column(columnDefinition="varchar(20) default 'NA'")
	private String referance_id;
	
	@Column(columnDefinition="varchar(20) default 0")
	private String tagadvice_status;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String passing_wt;
	
	@Column(columnDefinition="varchar(20) default 'NA'")
	private String receipt_criteria;
	
	@Column(columnDefinition="varchar(20) default 'NA'")
	private String po_status; /* New 14-04-2022*/
	
	@Size(max = 20)
	private String close_date; /* New 14-04-2022*/
	
	@Column(columnDefinition = "Decimal(10,3) default 0.000")
	private double total_qty; /* New 14-04-2022*/
	
	@Column(columnDefinition = "Decimal(10,3) default 0.000")
	private double total_qty_copy; /* New 14-04-2022*/
	
	@Column(columnDefinition="varchar(20) default 'NA'")
	private String staticuom; /* New 14-04-2022*/
	
	@Column(columnDefinition="varchar(20) default 'NA'")
	private String cal_no_of_advice; /* New 14-04-2022*/
	
	private int grn_status;
	
	private int unload_status;

	@Column(columnDefinition="varchar(10) default 'No'")
	private String pur_return_status; 
	
	@Column(columnDefinition="varchar(20) default 'NA'")
	private String purreturnid; 
	
	
	@Column(columnDefinition="varchar(10) default 'No'")
	private String channel_req; 
	
	@Column(columnDefinition="varchar(100) default ''")
	private String sup_channel; 
	
	@Column(columnDefinition="varchar(10) default ''")
	private String poitemnumber; 
	
	@Column(columnDefinition="varchar(10) default 'No'")
	private String consignment_type;
	
	@Column(columnDefinition="varchar(50) default 'No'")
	private String trans_borne_by_chgs;
	
	@Column(columnDefinition="TEXT")
	private String sup_channel_list;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String document_no;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String store_charges;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String store_charges_name;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="pur_Order",cascade = CascadeType.ALL)
	private Set<Pur_Order_Item_Details> pur_Order_Item_Details;
	
	@OneToOne(fetch = FetchType.EAGER,mappedBy="pur_Order",cascade =CascadeType.ALL)
	private Pur_Order_Terms_Con pur_Order_Terms_Con;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="pur_Order",cascade = CascadeType.ALL)
	private Set<Pur_Order_app_chgs> pur_Order_app_chgs;
	
    @OneToOne(fetch = FetchType.EAGER,mappedBy="pur_Order",cascade =CascadeType.ALL)
	private Pur_Order_BPDetails pur_Order_BPDetails;
	
	@OneToOne(fetch = FetchType.EAGER,mappedBy="pur_Order",cascade = CascadeType.ALL)
	private Pur_Order_Termination pur_Order_Terminations;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="pur_Order",cascade = CascadeType.ALL)
	private Set<Pur_Order_Termination_dyn> pur_Order_Terminations_dyn;
	
	@OneToOne(fetch = FetchType.EAGER,mappedBy="pur_Order",cascade = CascadeType.ALL)
	private Pur_Order_Trans_Info pur_Order_Trans_Infos;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="pur_Order",cascade = CascadeType.ALL)
	private Set<Pur_Order_broker> pur_Order_broker;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="pur_Order",cascade = CascadeType.ALL)
	private Set<Pur_Order_Doc> pur_Order_docs;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="pur_Order",cascade = CascadeType.ALL)
	private Set<Pur_Order_Trans_Chgs_dyn> pur_Order_Trans_Chgs_dyn;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="pur_Order",cascade = CascadeType.ALL)
	private Set<Pur_order_terms_conditions> pur_order_terms_conditions;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="pur_Order",cascade = CascadeType.ALL)
	private Set<Pur_order_store_chgs> pur_order_store_chgs;
	

}
