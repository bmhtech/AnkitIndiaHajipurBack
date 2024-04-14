package com.AnkitIndia.jwtauthentication.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name="Stk_Transfer_Challan_BusinessUnit_Dtls")
public class Stk_Transfer_Challan_BusinessUnit_Dtls extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String stk_challan_id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String stk_challan_no;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String slno;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String business_unit;
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String business_unitname;
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String cp_name;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String cp_contact;
	
	@ManyToOne(cascade= CascadeType.ALL)
	@JoinColumn(name = "stc_id")
	private Stk_Transfer_Challan stk_Transfer_Challan;
	
	@OneToOne(fetch = FetchType.EAGER,mappedBy="stk_Transfer_Challan_BusinessUnit_Dtls",cascade = CascadeType.ALL)
	private Stk_transfer_grn_bu_dtls stk_transfer_grn_bu_dtls;
}
