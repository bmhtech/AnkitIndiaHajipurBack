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
@Table(name="Stock_Transfer_Trans_Info")
public class Stock_Transfer_Trans_Info extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String order_id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String order_no;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String trans_borne_by;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String mode_of_trans;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String charge_code;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String trans_code;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String transporters;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String payment_term;
	
	@OneToOne(cascade= CascadeType.ALL)
	@JoinColumn(name = "st_id")
	private Stock_Transfer stock_Transfer;
}
