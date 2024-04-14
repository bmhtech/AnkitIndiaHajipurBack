package com.AnkitIndia.jwtauthentication.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name="Sales_Invoice_Shipment_Dtls")
public class Sales_Invoice_Shipment_Dtls extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String invoice_id;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String invoice_no;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String shipaddr;
	
	@Column(columnDefinition="varchar(500) default 0")
	private String shipdtls;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String paytoaddr;
	
	@Column(columnDefinition="varchar(500) default 0")
	private String paytodtls;
	
	@ManyToOne(cascade= CascadeType.ALL)
	@JoinColumn(name = "si_id")
	private Sales_Invoice sales_Invoice;
}
