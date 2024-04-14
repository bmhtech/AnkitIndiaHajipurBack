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
@Table(name="Store_inv_charge_master_dtls")
public class Store_inv_charge_master_dtls extends CommonProperties{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String store_charge_id;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String store_charge_name;

	@Column(columnDefinition="varchar(40) default 'NA'")
	private String store_charge_ac;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String store_charge_ac_name;
	
	@ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "s_id")
    private Store_inv_charge_master store_inv_charge_master;
}
