package com.AnkitIndia.jwtauthentication.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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

@Table(name="Pur_ord_item_dtls_track")

public class Pur_ord_item_dtls_track {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String pur_orderid;
	
	private int pur_dyn_id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String uadvice_id;
	
	@Column(columnDefinition = "Decimal(10,3) default 0.000")
	private double mat_wt;

	


	
	@OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "wmula_id")
    private Wm_unload_advice_item_dtls wm_unload_advice_item_dtls;
	
	
}
