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
@Table(name="Stock_Transfer")
public class Stock_Transfer extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String order_id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String order_no;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String ref_type;
	
	@Column(columnDefinition="varchar(20) default '0'")
	private String order_date;
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String order_point;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String business_unit;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String delivery_business_unit;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String loading_point;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String unloading_point;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String weightment_req;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String tax_info;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String enway_bill;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String service_item;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String shipment_mode;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String reference;
	
	@Column(columnDefinition="varchar(150) default '0'")
	private String remarks;
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String confirmed_by;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String approval;
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String reason;
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String approved_remarks;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String app_chgs_id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String order_status;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String billing_req;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String applicable_charges_id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String passing_wt;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String reference_id;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean loadunload_status;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String stk_grn_status;
	
	@OneToOne(fetch = FetchType.EAGER,mappedBy="stock_Transfer",cascade = CascadeType.ALL)
	private Stock_Transfer_Trans_Info stock_Transfer_Trans_Info;
	
	@OneToOne(fetch = FetchType.EAGER,mappedBy="stock_Transfer",cascade = CascadeType.ALL)
	private Stock_Transfer_Summary stock_Transfer_Summary;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="stock_Transfer",cascade = CascadeType.ALL)
	private Set<Stock_Transfer_Summary_dyn> stock_Transfer_Summary_dyn;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="stock_Transfer",cascade = CascadeType.ALL)
	private Set<Stock_Transfer_Item_Dtls> stock_Transfer_Item_Dtls;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="stock_Transfer",cascade = CascadeType.ALL)
	private Set<Stock_transfer_resource_cost> stock_transfer_resource_cost;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="stock_Transfer",cascade = CascadeType.ALL)
	private Set<Stock_transfer_doc> stock_transfer_doc;
	
	@OneToOne(fetch = FetchType.EAGER,mappedBy="stock_Transfer",cascade = CascadeType.ALL)
	private Stock_transfer_terminations stock_transfer_terminations;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="stock_Transfer",cascade = CascadeType.ALL)
	private Set<Stock_transfer_terminations_dyn> stock_transfer_terminations_dyn;
}
