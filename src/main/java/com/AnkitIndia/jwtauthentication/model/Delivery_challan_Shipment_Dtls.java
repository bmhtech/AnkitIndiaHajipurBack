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
@Table(name="Delivery_challan_Shipment_Dtls")
public class Delivery_challan_Shipment_Dtls extends CommonProperties{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String delivery_cid;
	
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String ship_addr;
	
	@Column(columnDefinition="varchar(250) default 'NA'")
	private String ship_details;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String pay_addr;
	
	@Column(columnDefinition="varchar(250) default 'NA'")
	private String pay_details;
	
	@OneToOne(cascade= CascadeType.ALL)
	@JoinColumn(name = "dc_id")
    private Delivery_challan delivery_challan;
}
