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
@Table(name="Store_inv_charge_master")
public class Store_inv_charge_master extends CommonProperties{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String store_charge_id;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String store_charge_desc;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy="store_inv_charge_master",cascade = CascadeType.ALL)
	private Set<Store_inv_charge_master_dtls> store_inv_charge_master_dtls;
}
