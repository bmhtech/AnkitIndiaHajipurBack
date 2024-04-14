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
@Table(name="Wm_loading_advice_Shipment_Dtls")
public class Wm_loading_advice_Shipment_Dtls extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String advice_id;
	
	@Column(columnDefinition = "varchar(50) default 0")
	private String advice_no;
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String ship_addr;
    
	@Column(columnDefinition = "varchar(250) default 'NA'")
	private String ship_details;
    
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String pay_addr;
    
	@Column(columnDefinition = "varchar(250) default 'NA'")
	private String pay_details;
           
	@OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "wl_id")
    private  Wm_loading_advice wm_loading_advice;
}
