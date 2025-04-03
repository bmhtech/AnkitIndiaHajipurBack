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
@Table(name="Pur_good_receipt")
/*@NamedStoredProcedureQueries({
	@NamedStoredProcedureQuery(name = "Pur_good_receipt.callItemStockMaintain", 
	  procedureName = "callItemStockMaintain", parameters = {
	    @StoredProcedureParameter(mode = ParameterMode.IN, name = "advitemqty", type = String.class),
	    @StoredProcedureParameter(mode = ParameterMode.IN, name = "advpackqty", type = String.class),
	    @StoredProcedureParameter(mode = ParameterMode.IN, name = "advitemcode", type = String.class),
	    @StoredProcedureParameter(mode = ParameterMode.IN, name = "advpackcode", type = String.class),
	    @StoredProcedureParameter(mode = ParameterMode.IN, name = "bunit", type = String.class),
	    @StoredProcedureParameter(mode = ParameterMode.IN, name = "finyear", type = String.class),
	    @StoredProcedureParameter(mode = ParameterMode.OUT, name = "result_out", type = Integer.class)})
})*/
public class Pur_good_receipt extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String grn_id;
	
	@Column(columnDefinition = "varchar(50) default '0'",name="grn_no" , unique = true, nullable = false)
	private String grn_no;

	@Column(columnDefinition="varchar(50) default '0'")
	private String grnno;
	
	@Column(columnDefinition = "varchar(50) default '0'")
	private String b_unit;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String b_unitname;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String grn_date;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String grndate;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String supplier_name;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String supplier;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private boolean item_type;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String purchase_type;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String purchase_typename;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String purchase_subtype;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String referance_type;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String sales_process;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String sales_order;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String vehicle_id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String vehicle_no;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String applicable_charges_id;
	
	@Column(columnDefinition="TEXT")
	private String remarks;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String grn_status;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean brokerage_active;
	
	@Column(columnDefinition = "varchar(50) default '0'")
	private String referance_id;
	
	@Column(columnDefinition = "varchar(50) default '0'")
	private String receipt_criteria;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean multiunloadadvice;
	
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String bill_status;
	
	@Column(columnDefinition="varchar(10) default 'No'")
	private String pur_return_status;
	
	@Column(columnDefinition="varchar(20) default 'NA'")
	private String purreturnid;
	
	//@Column(columnDefinition="int(1) default '0'")
	//private int grnpartytag;
	
	@OneToOne(fetch=FetchType.EAGER,mappedBy="pur_good_receipt",cascade=CascadeType.ALL)
	private Pur_good_receipt_Business_Partner_details pur_good_receipt_Business_Partner_details;
	
	@OneToMany(fetch=FetchType.EAGER,mappedBy="pur_good_receipt",cascade=CascadeType.ALL)
	private Set<Pur_good_receipt_item_details> pur_good_receipt_item_details;
	
	//Static
	@OneToOne(fetch = FetchType.EAGER,mappedBy="pur_good_receipt",cascade =CascadeType.ALL)
	private Pur_good_receipt_driver_dtls pur_good_receipt_driver_dtls;

	
	@OneToMany(fetch=FetchType.EAGER,mappedBy="pur_good_receipt",cascade=CascadeType.ALL)
	private Set<Pur_good_receipt_resource_cost> pur_good_receipt_resource_cost;
	
	@OneToOne(fetch=FetchType.EAGER,mappedBy="pur_good_receipt",cascade=CascadeType.ALL)
	private Pur_good_reciept_trans_info pur_good_reciept_trans_info;
	
	@OneToOne(fetch=FetchType.EAGER,mappedBy="pur_good_receipt",cascade=CascadeType.ALL)
	private Pur_goods_receipt_other_information pur_goods_receipt_other_information;
	
	@OneToMany(fetch=FetchType.EAGER,mappedBy="pur_good_receipt",cascade=CascadeType.ALL)
	private Set<Pur_good_receipt_doc> pur_good_receipt_docs;
	
	@OneToMany(fetch=FetchType.EAGER,mappedBy="pur_good_receipt",cascade=CascadeType.ALL)
	private Set<Pur_good_receipt_broker> pur_good_receipt_broker;
	
}
