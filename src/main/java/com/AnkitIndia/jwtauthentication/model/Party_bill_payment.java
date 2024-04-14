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
@Table(name="Party_bill_payment")
public class Party_bill_payment extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String pbill_id;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String pbill_no;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String party;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String partyname;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String business_unit;
	
	@Column(columnDefinition="varchar(100) default 0")
	private String business_unitname;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String pledgerid;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String pledgername;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String pay_to;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String pay_to_lname;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String entrydate;
	
	@Column(columnDefinition="Double(20,2) default 0.00")
	private double total_billamt;
	
	@Column(columnDefinition="Double(20,2) default 0.00")
	private double total_adjamt;
	
	@Column(columnDefinition="Double(20,2) default 0.00")
	private double total_dueamt;
	
	@Column(columnDefinition="Double(20,2) default 0.00")
	private double total_payamt;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="bill_payment",cascade = CascadeType.ALL)
	private Set<Party_bill_payment_details> party_bill_payment_details;
}
