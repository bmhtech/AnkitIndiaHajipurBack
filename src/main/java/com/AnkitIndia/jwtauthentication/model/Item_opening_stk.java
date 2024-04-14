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
@Table(name="Item_opening_stk")
public class Item_opening_stk {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String transe_id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String business_unit;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String tdate;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String item_type;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String company_id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String fin_year;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String username;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="opening_stk",cascade = CascadeType.ALL)
	private Set<Item_opening_stk_dtls> item_opening_stk_dtls;
	
}
