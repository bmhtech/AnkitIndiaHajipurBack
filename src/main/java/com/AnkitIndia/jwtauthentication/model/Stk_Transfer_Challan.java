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
@Table(name="Stk_Transfer_Challan")
public class Stk_Transfer_Challan extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String stk_challan_id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String stk_challan_no;
	
	@Column(columnDefinition="varchar(20) default '0'")
	private String stk_challan_date;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String cust_ref_doc_no;
	
	@Column(columnDefinition="varchar(20) default '0'")
	private String stk_challan_date2;
	
	@Column(columnDefinition="varchar(500) default '0'")
	private String remark;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String confirmed_by;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String approval;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String reason;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String ref_type;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String business_unit;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String delivery_business_unit;
	
	@Column(columnDefinition = "Decimal(10,2)")
	private double grand_total;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String order_point;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String billing_req;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String passing_wt;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String reference_id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String challan_status;
	
	@Column(columnDefinition="varchar(50) default 'NO'")
	private String saleinvoice_status;
	
	private int unload_status;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String sales_inv_id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String weighment_required;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String vehicle_type;
	
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="stk_Transfer_Challan",cascade = CascadeType.ALL)
	private Set<Stk_Transfer_Challan_Item_Dtls> stk_Transfer_Challan_Item_Dtls;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="stk_Transfer_Challan",cascade = CascadeType.ALL)
	private Set<Stk_Transfer_Challan_BusinessUnit_Dtls> stk_Transfer_Challan_BusinessUnit_Dtls;
	
	@OneToOne(fetch = FetchType.EAGER,mappedBy="stk_Transfer_Challan",cascade = CascadeType.ALL)
	private Stk_Transfer_Challan_Shipment_Dtls stk_Transfer_Challan_Shipment_Dtls;
	
	@OneToOne(fetch = FetchType.EAGER,mappedBy="stk_Transfer_Challan",cascade = CascadeType.ALL)
	private Stk_Transfer_Challan_Trans_Info stk_Transfer_Challan_Trans_Info;
	
	@OneToOne(fetch = FetchType.EAGER,mappedBy="stk_Transfer_Challan",cascade = CascadeType.ALL)
	private Stk_Transfer_Challan_Weight_Dtl stk_Transfer_Challan_Weight_Dtl;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="stk_Transfer_Challan",cascade = CascadeType.ALL)
	private Set<Stk_Transfer_Challan_Docs> stk_Transfer_Challan_Docs;
	
	@OneToOne(fetch = FetchType.EAGER,mappedBy="stk_Transfer_Challan",cascade = CascadeType.ALL)
	private Stk_transfer_grn stk_transfer_grn;
	
	//Static
	@OneToOne(fetch = FetchType.EAGER,mappedBy="stk_Transfer_Challan",cascade =CascadeType.ALL)
	private Vehicle_weighment_load_unload vehicle_weighment_load_unload;
	
	@OneToOne(fetch = FetchType.EAGER,mappedBy="stk_Transfer_Challan",cascade = CascadeType.ALL)
	private Stk_transfer_grn_other_info stk_transfer_grn_other_info;
	
	@OneToOne(fetch = FetchType.EAGER,mappedBy="stk_Transfer_Challan",cascade = CascadeType.ALL)
	private Stk_transfer_grn_resource_cost stk_transfer_grn_resource_cost;
	
	
}
