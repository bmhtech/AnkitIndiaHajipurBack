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
import javax.persistence.JoinColumn;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Stk_transfer_grn")
public class Stk_transfer_grn extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String stk_grn_id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String stk_grn_no;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String stk_grn_date;	
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String b_unit;
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String b_unitname;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String item_sub_type;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String item_type;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String referance_type;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String vehicle_id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String vehicle_no;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String applicable_charges_id;
	
	@Column(columnDefinition="varchar(500) default '0'")
	private String remarks;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean brokerage_active;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String reference_id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String reference_status;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String receipt_criteria;
	
	private int stk_pur_inv_status;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String rec_b_unit;
	
	@OneToOne(cascade= CascadeType.ALL)
	@JoinColumn(name = "stc_id")
	private Stk_Transfer_Challan stk_Transfer_Challan;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="sTransfer_grn",cascade = CascadeType.ALL)
	private Set<Stk_transfer_grn_item_details> stk_transfer_grn_item_details;
	
	@OneToOne(fetch = FetchType.EAGER,mappedBy="sTransfer_grn",cascade = CascadeType.ALL)
	private Stk_transfer_grn_trans_info stk_transfer_grn_trans_info;
	
	@OneToOne(fetch = FetchType.EAGER,mappedBy="sTransfer_grn",cascade = CascadeType.ALL)
	private Stk_transfer_grn_bu_dtls stk_transfer_grn_bu_dtls;
	
	@OneToOne(fetch = FetchType.EAGER,mappedBy="sTransfer_grn",cascade = CascadeType.ALL)
	private Stk_transfer_grn_other_info stk_transfer_grn_other_info;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="sTransfer_grn",cascade = CascadeType.ALL)
	private Set<Stk_transfer_grn_resource_cost> stk_transfer_grn_resource_cost;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="sTransfer_grn",cascade = CascadeType.ALL)
	private Set<Stk_transfer_grn_docs> stk_transfer_grn_docs;
	
	/*@OneToMany(fetch = FetchType.EAGER,mappedBy="sTransfer_grn",cascade = CascadeType.ALL)
	private Set<Stk_transfer_grn_broker_details> stk_transfer_grn_broker_details;*/
}
