package com.AnkitIndia.jwtauthentication.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name="Stk_transfer_grn_trans_info")
public class Stk_transfer_grn_trans_info extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String stk_grn_id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String stk_grn_no;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String trans_borne_by;
	
	@Column(columnDefinition = "Double(10,2) default 0.00")
	private double cash_limit;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String mode_of_trans;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String transporter_code;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String transportation_rate;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String payment_mode;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String payment_term;
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String bank_name;
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String acc_name;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String acc_no;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String branch;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String iban;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String bic_swift_code;
	
	@OneToOne(cascade= CascadeType.ALL)
	@JoinColumn(name = "stkgrn_id")
	private Stk_transfer_grn sTransfer_grn;
	
	@OneToOne(cascade= CascadeType.ALL)
	@JoinColumn(name = "stc_id")
	private Stk_Transfer_Challan_Trans_Info stk_Transfer_Challan_Trans_Info;
	
}
