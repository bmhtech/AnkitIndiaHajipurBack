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
@Table(name="Jw_grn_itemtag")
public class Jw_grn_itemtag extends CommonProperties{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String jw_grn_tag;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String grn_id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String grn_no;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String grn_date;
	
	@Column(columnDefinition="varchar(200) default '0'")
	private String supplier_name;
	
	@Column(columnDefinition = "Double(10,3) default 0.000")
	private double totalqty;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="jw_grn_itemtag",cascade = CascadeType.ALL)
	private Set<Jw_grn_partytag_details> jw_grn_partytag_details;
}
