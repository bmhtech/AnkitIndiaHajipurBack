package com.AnkitIndia.jwtauthentication.model;

import java.sql.Date;
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
@Table(name="Stk_Transfer_Challan_Weight_Dtl")
public class Stk_Transfer_Challan_Weight_Dtl extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String stk_challan_id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String stk_challan_no;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String own_uom;
	
	@Column(columnDefinition = "Decimal(10,2) default 0.00")
	private double own_gross;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String own_tare;
	
	@Column(columnDefinition = "Decimal(10,2) default 0.00")
	private double own_net;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String eway_bill_no;
	
	private Date  own_date;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String own_slip_no;
	
	@OneToOne(cascade= CascadeType.ALL)
	@JoinColumn(name = "stc_id")
	private Stk_Transfer_Challan stk_Transfer_Challan;
}
