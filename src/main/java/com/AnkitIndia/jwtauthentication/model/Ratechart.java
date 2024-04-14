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
@Table(name="Ratechart")

public class Ratechart extends CommonProperties
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(30) default 'NA'")
	private String rate_code;
	
	@Column(columnDefinition="varchar(30) default 'NA'")
	private String rate_id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String b_unit;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String business_unitname;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String date;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String valid_date;
	
	//Dynamic
    @OneToMany(fetch = FetchType.EAGER,mappedBy="ratechart",cascade =CascadeType.ALL) 
    private Set<Item_rate_dtls> item_rate_dtls;
}
